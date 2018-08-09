package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private CarRepository carRepository;

    public DefaultCarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Get selectedCar list by driver id.
     *
     * @param carId
     * @return selected car list
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException {
        return null;
    }


    /**
     * Get selectedCar list by driver id.
     *
     * @param carDO
     * @return created CarDO
     * @throws ConstraintsViolationException if a car already exists with the given license plate,...
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException {
        CarDO car;
        try {
            car = carRepository.save(carDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }


    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException {
        CarDO carDO = findDriverChecked(carId);
        carDO.setDeleted(true);
    }


    /**
     * Get selectedCar list by driver id.
     *
     * @param carDO
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void updateCarSpecifications(CarDO carDO) throws EntityNotFoundException {
        CarDO car = findDriverChecked(carDO.getId());

    }

    /**
     * Get selectedCar list by driver id.
     *
     * @param driverId
     * @return selected car list
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public List<CarDO> findSelectedCarsByDriverID(Long driverId) throws EntityNotFoundException {
        return carRepository.findSelectedCarsByDriverID(driverId);
    }


    private CarDO findDriverChecked(Long carId) throws EntityNotFoundException {
        return carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }
}
