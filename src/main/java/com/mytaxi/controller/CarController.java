package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DontHaveRightException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.OfflineDriverException;
import com.mytaxi.service.car.CarService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{

    @Autowired
    private final CarService carService;


    public CarController(final CarService carService)
    {
        this.carService = carService;
    }


    @GetMapping("/{carId}")
    public ResponseEntity<CarDTO> getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        CarDO carDO = carService.find(carId);
        if (carDO != null)
        {
            return new ResponseEntity<>(CarMapper.makeCarDTO(carDO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }


    @PostMapping
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        if (carDO != null)
        {
            return new ResponseEntity<>(CarMapper.makeCarDTO(carService.create(carDO)), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }


    @DeleteMapping("/{carId}")
    public ResponseEntity<CarDO> deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException, DontHaveRightException, OfflineDriverException
    {
        carService.delete(carId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/{carId}")
    public void updateCarSpecification(@Valid @RequestBody CarDO carDO)
        throws EntityNotFoundException
    {
        carService.updateCarSpecifications(carDO);
    }

}
