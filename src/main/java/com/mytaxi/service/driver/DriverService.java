package com.mytaxi.service.driver;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DontHaveRightException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.OfflineDriverException;
import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    void selectCar(Long driverId, Long carId) throws EntityNotFoundException, OfflineDriverException, CarAlreadyInUseException;

    void deselectCar(Long driverId, Long carId) throws EntityNotFoundException, OfflineDriverException, DontHaveRightException;

    List<DriverDO> searchDriverByDriverSpec(DriverDTO driver);

}
