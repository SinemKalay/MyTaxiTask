package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

public interface CarService {

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    void updateCarSpecifications(CarDO carDO) throws EntityNotFoundException;

    List<CarDO> findSelectedCarsByDriverID(Long driverID) throws EntityNotFoundException;

}
