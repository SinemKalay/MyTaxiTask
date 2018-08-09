package com.mytaxi.controller;


import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {


    private final CarService carService;


    @Autowired
    public CarController(final CarService carService)
    {
        this.carService = carService;
    }


    @GetMapping("/{carId}")
    public CarDTO getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));

    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }


    @PutMapping("/{carId}")
    public void updateCarSpecification(
            @Valid @PathVariable long carId, @RequestParam double longitude, @RequestParam double latitude)
            throws ConstraintsViolationException, EntityNotFoundException
    {
       // carService.update(driverId, longitude, latitude);
    }
}
