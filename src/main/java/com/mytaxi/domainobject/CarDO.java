package com.mytaxi.domainobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytaxi.domainvalue.EngineType;
import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "license_plate", columnNames = {"licensePlate"})
)
public class CarDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "License plate can not be null!")
    private String licensePlate;

    @Column
    private Integer seatCount = 0;

    @Enumerated(EnumType.STRING)
    @Column
    private EngineType engineType;

    @Column
    private Boolean convertible = false;

    @Column
    private Double rating = 0.0;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateUpdated = ZonedDateTime.now();

    @Column
    private Boolean deleted = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Man_ID")
    private ManufacturerDO manufacturerDO;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Driver_ID")
    @JsonIgnore
    private DriverDO driverDO;

    //    @OneToOne(cascade=CascadeType.PERSIST)
    //    @JoinColumn(name = "DRIVER_ID")
    //    private DriverDO driverDO;


    public CarDO()
    {
    }


    public CarDO(String licensePlate, DriverDO driverDO)
    {
        this.licensePlate = licensePlate;
        this.driverDO = driverDO;
        this.seatCount = 0;
        this.convertible = false;
        this.rating = 0.0;
        this.dateUpdated = null;
        this.deleted = false;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(Integer seatCount)
    {
        this.seatCount = seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public void setConvertible(Boolean convertible)
    {
        this.convertible = convertible;
    }


    public Double getRating()
    {
        return rating;
    }


    public void setRating(Double rating)
    {
        this.rating = rating;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public void setEngineType(EngineType engineType)
    {
        this.engineType = engineType;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public ZonedDateTime getDateCoordinateUpdated()
    {
        return dateUpdated;
    }


    public void setDateCoordinateUpdated(ZonedDateTime dateCoordinateUpdated)
    {
        this.dateUpdated = dateCoordinateUpdated;
    }


    public ZonedDateTime getDateUpdated()
    {
        return dateUpdated;
    }


    public void setDateUpdated(ZonedDateTime dateUpdated)
    {
        this.dateUpdated = dateUpdated;
    }


    public ManufacturerDO getManufacturerDO()
    {
        return manufacturerDO;
    }


    public void setManufacturerDO(ManufacturerDO manufacturerDO)
    {
        this.manufacturerDO = manufacturerDO;
    }


    public DriverDO getDriverDO()
    {
        return driverDO;
    }


    public void setDriverDO(DriverDO driverDO)
    {
        this.driverDO = driverDO;
    }
}
