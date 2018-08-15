package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarDO, Long>, JpaSpecificationExecutor<CarDO>
{
    Optional<CarDO> findByIdAndDeleted(Long carId, Boolean deleted);

}
