package com.mytaxi.specificationobject;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.util.StaticValues;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification implements Specification<CarDO>
{

    private CarDO filterCarDTO;


    public CarSpecification(CarDTO filterCarDTO)
    {
        super();
        this.filterCarDTO = CarMapper.makeCarDO(filterCarDTO);
    }


    @Override
    public Predicate toPredicate(Root<CarDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
    {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (filterCarDTO != null)
        {
            if (filterCarDTO.getLicensePlate() != null)
            {
                Predicate licencePlatePre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_LICENSEPLATE), filterCarDTO.getLicensePlate());
                predicates.add(licencePlatePre);
            }
            if (filterCarDTO.getManufacturerDO() != null)
            {
                Predicate manufacturerPre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_MANUFACTURER), filterCarDTO.getManufacturerDO());
                predicates.add(manufacturerPre);
            }
            Predicate notDeletedPre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_DELETED), false);
            predicates.add(notDeletedPre);

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
        return null;
    }
}
