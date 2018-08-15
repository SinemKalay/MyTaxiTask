package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DontHaveRightException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;
import com.mytaxi.specificationobject.CarSpecification;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private final DriverRepository driverRepository;


    public DefaultCarService(final CarRepository carRepository, final DriverRepository driverRepository)
    {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }


    /**
     * Get car  by car id.
     *
     * @param carId
     * @return car
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    /**
     * Create car with given car attributes.
     *
     * @param carDO
     * @return car
     * @throws ConstraintsViolationException if a car already exists with the given license plate,...
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        try
        {

            return carRepository.save(carDO);

        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }


    /**
     * Soft delete an existing car by id and also remove driver to break the relation.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setDeleted(true);
        carDO.setDriverDO(null);
    }


    /**
     * Get selectedCar list by driver id.
     *
     * @param carDO
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void updateCarSpecifications(CarDO carDO) throws EntityNotFoundException
    {
        findCarChecked(carDO.getId());
        carRepository.save(carDO);
    }


    /**
     * Set car as selected by driver
     * Car should be false as deleted field value
     *
     * @param driver
     * @param carId
     * @throws EntityNotFoundException  if no driver with the given id was found.
     * @throws CarAlreadyInUseException if requested car is in already using.
     */
    @Override
    @Transactional
    public void selectCar(DriverDO driver, Long carId) throws CarAlreadyInUseException
    {

        CarDO car = carRepository.findByIdAndDeleted(carId, false).get();

        if (car.getDriverDO() != null)
        {
            throw new CarAlreadyInUseException(carId + " id car is already in use for another driver");
        }
        car.setDriverDO(driver);
    }


    /**
     * Set car as deselected
     * Car should be false as deleted field value
     *
     * @param driverId
     * @param carId
     * @throws EntityNotFoundException if no driver with the given id was found.
     * @throws DontHaveRightException  if driver attempts to deselect a car which is not selected by her/him
     */
    @Override
    @Transactional
    public void deselectCar(Long driverId, Long carId) throws EntityNotFoundException, DontHaveRightException
    {

        CarDO car = carRepository.findByIdAndDeleted(carId, false).get();

        if (car.getDriverDO() == null)
        {
            throw new EntityNotFoundException(carId + " id car is not an assigned car");
        }
        else if (car.getDriverDO().getId() != driverId)
        {
            throw new DontHaveRightException(driverId + " doesn't have right to deselect " + carId + " id car");
        }
        car.setDriverDO(null);

    }


    /**
     * Find suitable CarDO items  then return their drivers as a list
     *
     * @param car for gather carAttributes as parameter
     */
    @Override
    public List<DriverDO> searchDriverByCarSpec(CarDTO car)
    {

        Specification<CarDO> spec = new CarSpecification(car);
        List<CarDO> listOfCar = carRepository.findAll(spec);
        if (listOfCar != null)
        {
            return createListOfCarDrivers(listOfCar);
        }
        return null;
    }


    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }


    public List<DriverDO> createListOfCarDrivers(List<CarDO> listOfCar)
    {
        List<DriverDO> listOfDrivers = new ArrayList<>();
        Predicate<CarDO> isCarSelected = d -> d.getDriverDO() != null;
        Consumer<CarDO> addDriver = c -> driverRepository.findById(c.getDriverDO().getId()).ifPresent(driver -> listOfDrivers.add(driver));
        listOfCar.stream().filter(isCarSelected).forEach(addDriver);
        return listOfDrivers;
    }


}
