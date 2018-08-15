package com.mytaxi.domainobject;

import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import java.time.ZonedDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "driver",
    uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = {"username"})
)
public class DriverDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "Username can not be null!")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "Password can not be null!")
    private String password;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Embedded
    private GeoCoordinate coordinate;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OnlineStatus onlineStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driverDO")
    private Set<CarDO> selectedCars;


    private DriverDO()
    {
    }


    public DriverDO(String username, String password, OnlineStatus onlineStatus, Set<CarDO> selectedCars)
    {
        this.username = username;
        this.password = password;
        this.deleted = false;
        this.coordinate = null;
        this.dateCoordinateUpdated = null;
        this.onlineStatus = onlineStatus;
        this.selectedCars = selectedCars;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public OnlineStatus getOnlineStatus()
    {
        return onlineStatus;
    }


    public void setOnlineStatus(OnlineStatus onlineStatus)
    {
        this.onlineStatus = onlineStatus;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }


    public void setCoordinate(GeoCoordinate coordinate)
    {
        this.coordinate = coordinate;
        this.dateCoordinateUpdated = ZonedDateTime.now();
    }


    public Set<CarDO> getSelectedCars()
    {
        return selectedCars;
    }


    public void setSelectedCars(Set<CarDO> selectedCars)
    {
        this.selectedCars = selectedCars;
    }
}
