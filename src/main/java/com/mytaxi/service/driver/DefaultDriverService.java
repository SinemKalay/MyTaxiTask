package com.mytaxi.service.driver;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DontHaveRightException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.OfflineDriverException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.specificationobject.DriverSpecification;
import java.util.List;
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
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    @Autowired
    private final DriverRepository driverRepository;

    @Autowired
    private CarService carService;


    public DefaultDriverService(final DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    /**
     * Select a car by a driver who is in online state
     *
     * @param driverId
     * @param carId    *
     * @throws EntityNotFoundException  if no driver with the given id was found.
     * @throws OfflineDriverException   if driver is not ONLINE
     * @throws CarAlreadyInUseException if requested car is in already using.
     */
    @Override
    @Transactional
    public void selectCar(Long driverId, Long carId) throws EntityNotFoundException, OfflineDriverException, CarAlreadyInUseException
    {

        if (isDriverOnline(driverId))
        {
            DriverDO driver = findDriverChecked(driverId);
            carService.selectCar(driver, carId);
        }
    }


    /**
     * Deselect a car by a driver who is in online state
     *
     * @param driverId
     * @param carId    *
     * @throws EntityNotFoundException if no driver with the given id was found.
     * @throws OfflineDriverException  if driver is not ONLINE
     * @throws DontHaveRightException  if driver attempts to deselect a car which is not selected by her/him
     */
    @Override
    @Transactional
    public void deselectCar(Long driverId, Long carId) throws EntityNotFoundException, OfflineDriverException, DontHaveRightException
    {

        if (isDriverOnline(driverId))
        {
            DriverDO driver = driverRepository.findById(driverId).get();
            carService.deselectCar(driverId, carId);
            //
            //            if (driver.getSelectedCars().contains(carDO))
            //            {
            //                driver.getSelectedCars().remove(carDO);
            //            }
        }
    }


    /**
     * Search for Driver
     * Search criterias: Drivers' username, onlineStatus, location and selectedCar attributes
     *
     * @param driver
     */
    @Override
    public List<DriverDO> searchDriverByDriverSpec(DriverDTO driver)
    {

        Specification<DriverDO> spec = new DriverSpecification(driver);
        return driverRepository.findAll(spec);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findByIdAndDeleted(driverId, false)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }


    private Boolean isDriverOnline(Long driverId) throws EntityNotFoundException, OfflineDriverException
    {

        DriverDO driver = findDriverChecked(driverId);
        if (driver.getOnlineStatus() == OnlineStatus.OFFLINE)
        {
            throw new OfflineDriverException("Driver is Offline");
        }
        return true;
    }


}
