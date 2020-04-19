package com.stackabuse.dtoexample.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stackabuse.dtoexample.dto.LocationDTO;
import com.stackabuse.dtoexample.dto.UserLocationDTO;
import com.stackabuse.dtoexample.service.MapService;

@RestController
@RequestMapping(value="/map")
public class MapController {
	@Autowired
	private MapService mapService;

    @GetMapping
    @ResponseBody
    public List<UserLocationDTO> getAllUsersLocation() {
    	List <UserLocationDTO> usersLocation = mapService.getAllUsersLocation();
        return usersLocation;
    }
    
    @GetMapping(value = "/{id}")
    @ResponseBody
    public UserLocationDTO getUserLocation(@PathVariable("id") Long id) {
        return mapService.getUserLocation(id);
    }
    
    @PostMapping(value = "/addLocation")
    @ResponseStatus(HttpStatus.OK)
    public void addLocation(@RequestBody LocationDTO locationDTO) {    
    	mapService.addLocation(locationDTO);
    }
    
    @GetMapping(value = "/allLocations")
    @ResponseBody
    public List<LocationDTO> getAllLocations() {
    	List <LocationDTO> locations = mapService.getAllLocations();
        return locations;
    }
    
}
