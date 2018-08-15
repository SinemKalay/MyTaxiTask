package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO
{
    //    @JsonIgnore
    private Long id;

    private String username;

    private String password;

    private OnlineStatus onlineStatus;

    private GeoCoordinate coordinate;

    private Set<CarDO> selectedCars;


    private DriverDTO()
    {
    }


    private DriverDTO(Long id, String username, String password, GeoCoordinate coordinate, OnlineStatus onlineStatus, Set<CarDO> selectedCars)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
        this.onlineStatus = onlineStatus;
        this.selectedCars = selectedCars;
    }


    public static DriverDTOBuilder newBuilder()
    {
        return new DriverDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }


    public OnlineStatus getOnlineStatus()
    {
        return onlineStatus;
    }


    public Set<CarDO> getSelectedCars()
    {
        return selectedCars;
    }


    public static class DriverDTOBuilder
    {
        private Long id;
        private String username;
        private String password;
        private GeoCoordinate coordinate;
        private OnlineStatus onlineStatus;
        private Set<CarDO> selectedCars;


        public DriverDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public DriverDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }


        public DriverDTOBuilder setPassword(String password)
        {
            this.password = password;
            return this;
        }


        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate)
        {
            this.coordinate = coordinate;
            return this;
        }


        public DriverDTOBuilder setOnlineStatus(OnlineStatus onlineStatus)
        {
            this.onlineStatus = onlineStatus;
            return this;
        }


        public DriverDTOBuilder setSelectedCars(Set<CarDO> selectedCars)
        {
            this.selectedCars = selectedCars;
            return this;
        }


        public DriverDTO createDriverDTO()
        {
            return new DriverDTO(id, username, password, coordinate, onlineStatus, selectedCars);
        }

    }
}
