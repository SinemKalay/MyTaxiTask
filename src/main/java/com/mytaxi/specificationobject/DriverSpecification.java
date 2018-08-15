package com.mytaxi.specificationobject;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.util.StaticValues;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class DriverSpecification implements Specification<DriverDO>
{

    private DriverDO filterDriverDO;


    public DriverSpecification(DriverDTO filterDriverDTO)
    {
        super();
        this.filterDriverDO = DriverMapper.makeDriverDO(filterDriverDTO);
    }


    @Override
    public Predicate toPredicate(Root<DriverDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
    {
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (filterDriverDO != null)
        {
            if (filterDriverDO.getUsername() != null)
            {
                Predicate usernamePre = criteriaBuilder.like(root.get(StaticValues.COLUMN_USERNAME), "%" + filterDriverDO.getUsername() + "%");
                predicates.add(usernamePre);
            }
            if (filterDriverDO.getCoordinate() != null)
            {
                Predicate coordinatePre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_COORDINATE), filterDriverDO.getCoordinate());
                predicates.add(coordinatePre);
            }
            if (filterDriverDO.getOnlineStatus() != null)
            {
                Predicate onlineStatusPre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_ONLINESTATUS), filterDriverDO.getOnlineStatus());
                predicates.add(onlineStatusPre);
            }

            Predicate notDeletedPre = criteriaBuilder.equal(root.get(StaticValues.COLUMN_DELETED), false);
            predicates.add(notDeletedPre);

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        }
        return null;

    }
}
