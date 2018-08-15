package com.mytaxi.service.car;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DontHaveRightException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    void updateCarSpecifications(CarDO carDO) throws EntityNotFoundException;

    void selectCar(DriverDO driver, Long carId) throws CarAlreadyInUseException;

    void deselectCar(Long driverId, Long carId) throws EntityNotFoundException, DontHaveRightException;

    List<DriverDO> searchDriverByCarSpec(CarDTO driver);

}
