package com.mytaxi.controller;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations for searching a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/searchDriver")
public class SearchDriverController
{

    private final DriverService driverService;

    private final CarService carService;


    @Autowired
    public SearchDriverController(final DriverService driverService, final CarService carService)
    {
        this.driverService = driverService;
        this.carService = carService;
    }


    @PostMapping("/byDriverAtt")
    public ResponseEntity<List<DriverDTO>> searchbyDriverAttributes(@RequestBody DriverDTO driver)
    {
        List<DriverDTO> drivers = DriverMapper.makeDriverDTOList(new ArrayList<DriverDO>(driverService.searchDriverByDriverSpec(driver)));
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }


    @PostMapping("/byCarAtt")
    public ResponseEntity<List<DriverDTO>> searchbyCarAttributes(@RequestBody CarDTO car)
    {
        List<DriverDTO> drivers = DriverMapper.makeDriverDTOList(new ArrayList<DriverDO>(carService.searchDriverByCarSpec(car)));
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }


}
