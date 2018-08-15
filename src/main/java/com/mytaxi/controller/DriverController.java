package com.mytaxi.controller;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DontHaveRightException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.OfflineDriverException;
import com.mytaxi.service.driver.DriverService;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;


    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDTO> getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = driverService.find(driverId);
        if (driverDO != null)
        {
            return new ResponseEntity<>(DriverMapper.makeDriverDTO(driverDO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        if (driverDO != null)
        {
            return new ResponseEntity<>(DriverMapper.makeDriverDTO(driverService.create(driverDO)), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public ResponseEntity<List<DriverDTO>> findDrivers(@RequestParam OnlineStatus onlineStatus)
    {
        return new ResponseEntity<>(DriverMapper.makeDriverDTOList(driverService.find(onlineStatus)), HttpStatus.OK);
    }


    @PutMapping("/selectCar/{driverId}")
    public ResponseEntity<Object> selectCar(@Valid @PathVariable long driverId, @RequestParam long carId)
        throws EntityNotFoundException, CarAlreadyInUseException, OfflineDriverException
    {

        driverService.selectCar(driverId, carId);

        return ResponseEntity.ok(new HashMap<String, String>()
        {{
            put("message", carId + " Id car has been selected by " + driverId + " id driver");
        }});
    }


    @PutMapping("/deselectCar/{driverId}")
    public ResponseEntity<Object> deselectCar(@Valid @PathVariable long driverId, @RequestParam long carId) throws EntityNotFoundException, OfflineDriverException,
                                                                                                                   DontHaveRightException
    {
        driverService.deselectCar(driverId, carId);

        return ResponseEntity.ok(new HashMap<String, String>()
        {{
            put("message", carId + " Id car has been released by " + driverId + " id driver");
        }});
    }
}
