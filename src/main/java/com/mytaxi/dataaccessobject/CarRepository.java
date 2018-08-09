package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<CarDO,Long>{

    List<CarDO> findSelectedCarsByDriverID(Long driverID);

}
