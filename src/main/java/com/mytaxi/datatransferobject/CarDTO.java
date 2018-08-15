package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.ManufacturerDO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{

    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String licensePlate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "DRIVER_ID")
    private DriverDO driverDO;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Man_ID")
    private ManufacturerDO manufacturerDO;


    public CarDTO()
    {

    }


    public CarDTO(Long id, String licensePlate, DriverDO driverDO, ManufacturerDO manufacturerDO)
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.driverDO = driverDO;
        this.manufacturerDO = manufacturerDO;
    }


    public static CarDTO.CarDTOBuilder newBuilder()
    {
        return new CarDTO.CarDTOBuilder();
    }


    @JsonProperty
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


    public DriverDO getDriverDO()
    {
        return driverDO;
    }


    public void setDriverDO(DriverDO driverDO)
    {
        this.driverDO = driverDO;
    }


    public ManufacturerDO getManufacturerDO()
    {
        return manufacturerDO;
    }


    public void setManufacturerDO(ManufacturerDO manufacturerDO)
    {
        this.manufacturerDO = manufacturerDO;
    }


    public static class CarDTOBuilder
    {

        private Long id;

        private String licensePlate;

        private DriverDO driverDO;

        private ManufacturerDO manufacturerDO;


        public CarDTOBuilder()
        {

        }


        public CarDTOBuilder(Long id, String licensePlate, DriverDO driver, ManufacturerDO manufacturerDO)
        {
            this.id = id;
            this.licensePlate = licensePlate;
            this.driverDO = driver;
            this.manufacturerDO = manufacturerDO;
        }


        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarDTOBuilder setDriver(DriverDO driver)
        {
            this.driverDO = driver;
            return this;
        }


        public CarDTOBuilder setManufacturer(ManufacturerDO manufacturerDO)
        {
            this.manufacturerDO = manufacturerDO;
            return this;
        }


        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate, driverDO, manufacturerDO);
        }

    }
}
