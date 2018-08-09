package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "License plate can not be null!")
    private String licensePlate;

    public CarDTO() {

    }

    public CarDTO(Long id, String licensePlate) {
        this.id = id;
        this.licensePlate = licensePlate;
    }

    public static CarDTO.CarDTOBuilder newBuilder()
    {
        return new CarDTO.CarDTOBuilder();
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public static class CarDTOBuilder {

        private Long id;

        private String licensePlate;

        public CarDTOBuilder() {

        }

        public CarDTOBuilder(Long id, String licensePlate) {
            this.id = id;
            this.licensePlate = licensePlate;
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

        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate);
        }

    }
}
