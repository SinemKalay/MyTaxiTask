package com.mytaxi.domainobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
    name = "manufacturer",
    uniqueConstraints = @UniqueConstraint(name = "mn_name", columnNames = {"manufacturerName"})
)
public class ManufacturerDO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Manufacturer name can not be null!")
    private String manufacturerName;

    @OneToMany(mappedBy = "manufacturerDO")
    @JsonIgnore
    private Set<CarDO> carModels;


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getManufacturerName()
    {
        return manufacturerName;
    }


    public void setManufacturerName(String manufacturerName)
    {
        this.manufacturerName = manufacturerName;
    }


    public Set<CarDO> getCarModels()
    {
        return carModels;
    }


    public void setCarModels(Set<CarDO> carModels)
    {
        this.carModels = carModels;
    }
}

