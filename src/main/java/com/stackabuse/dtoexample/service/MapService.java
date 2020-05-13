package com.stackabuse.dtoexample.service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackabuse.dtoexample.dto.LocationDTO;
import com.stackabuse.dtoexample.dto.UserLocationDTO;
import com.stackabuse.dtoexample.model.Location;
import com.stackabuse.dtoexample.model.User;
import com.stackabuse.dtoexample.repository.LocationRepository;
import com.stackabuse.dtoexample.repository.UserRepository;

@Service
public class MapService {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public UserLocationDTO getUserLocation(Long id) {
		return convertToUserLocationDTO(userRepository.findById(id).get());
	}


	public List<UserLocationDTO> getAllUsersLocation() {
		return ((List<User>) userRepository.findAll()).stream().map(this::convertToUserLocationDTO)
				.collect(Collectors.toList());
	}

	public List<UserLocationDTO> getAllUsersLocation_V2() {
		return ((List<User>) userRepository.findAll()).stream().map(this::convertToUserLocationDTO_V2)
				.collect(Collectors.toList());
	}
	
	private UserLocationDTO convertToUserLocationDTO(User user) {
		UserLocationDTO userLocationDTO = new UserLocationDTO();
		userLocationDTO.setUserId(user.getId());
		userLocationDTO.setUsername(user.getUsername());
		Location location = user.getLocation();
		userLocationDTO.setLat(location.getLat());
		userLocationDTO.setLng(location.getLng());
		userLocationDTO.setPlace(location.getPlace());
		return userLocationDTO;
	}
	
	private UserLocationDTO convertToUserLocationDTO_V2(User user) {
	    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		UserLocationDTO userLocationDTO = modelMapper.map(user, UserLocationDTO.class);
		return userLocationDTO;
	}
	

	public void addLocation(LocationDTO locationDto) {
		Location location = new ModelMapper().map(locationDto, Location.class);
		locationRepository.save(location);
	}
	
	public List<LocationDTO> getAllLocations() {
		return ((List<Location>) locationRepository.findAll())
				.stream()
				.map(obj -> modelMapper.map(obj, LocationDTO.class))
				.collect(Collectors.toList());
	}


}
