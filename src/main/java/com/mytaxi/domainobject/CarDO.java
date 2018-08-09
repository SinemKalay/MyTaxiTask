package com.mytaxi.domainobject;

import com.mytaxi.domainvalue.EngineType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(
        name = "car",
        uniqueConstraints = @UniqueConstraint(name = "license_plate", columnNames = {"licensePlate"})
)
public class CarDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "License plate can not be null!")
    private String licensePlate;

    @Column(nullable = false)
    private Integer seatCount=0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineType engineType;

    @Column(nullable = false)
    private Boolean convertible=false;

    @Column(nullable = false)
    private Double rating=0.0;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateUpdated = ZonedDateTime.now();

    @Column(nullable = false)
    private Boolean deleted = false;

    public CarDO(String licensePlate)
    {
        this.licensePlate=licensePlate;
        this.seatCount=0;
        this.convertible=false;
        this.rating=0.0;
        this.dateUpdated=null;
        this.deleted = false;
    }

    public Long getId() {     return id;    }

    public void setId(Long id) {      this.id = id;    }

    public String getLicensePlate() {     return licensePlate;    }

    public void setLicensePlate(String licensePlate) {     this.licensePlate = licensePlate;    }

    public Integer getSeatCount() {     return seatCount;    }

    public void setSeatCount(Integer seatCount) {      this.seatCount = seatCount;    }

    public Boolean getConvertible() {     return convertible;    }

    public void setConvertible(Boolean convertible) {    this.convertible = convertible;    }

    public Double getRating() {     return rating;    }

    public void setRating(Double rating) {    this.rating = rating;    }

    public EngineType getEngineType() {   return engineType;   }

    public void setEngineType(EngineType engineType) {     this.engineType = engineType;   }

    public Boolean getDeleted() {        return deleted;    }

    public void setDeleted(Boolean deleted) {  this.deleted = deleted; }

    public ZonedDateTime getDateCoordinateUpdated() {
        return dateUpdated;
    }

    public void setDateCoordinateUpdated(ZonedDateTime dateCoordinateUpdated) {
        this.dateUpdated = dateCoordinateUpdated;
    }


}
