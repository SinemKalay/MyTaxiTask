package com.mytaxi.domainobject;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(
        name = "manufacturer",
        uniqueConstraints = @UniqueConstraint(name = "mn_name", columnNames = {"manufacturerName"})
)
public class ManufacturerDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Manufacturer name can not be null!")
    private String manufacturerName;

    @OneToMany(mappedBy = "manufacturer")
    @JsonIgnore
    private Set<CarDO> carModels;

    public Long getId() {       return id;   }

    public void setId(Long id) {     this.id = id;  }

    public String getManufacturerName() {      return manufacturerName;   }

    public void setManufacturerName(String manufacturerName) {     this.manufacturerName = manufacturerName;   }

    public Set<CarDO> getCarModels() {     return carModels;  }

    public void setCarModels(Set<CarDO> carModels) {      this.carModels = carModels;  }
}

