package com.sda.project.service;

import com.sda.project.controller.exception.ResourceNotFoundException;
import com.sda.project.model.Location;
import com.sda.project.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private static final Logger log = LoggerFactory.getLogger(LocationService.class);

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location save(Location location) {
        log.info("save location {}", location);

        return locationRepository.save(location);
    }

    public List<Location> findAll() {
        log.info("finding all properties");

        return locationRepository.findAll();
    }

    public Location findById(Long id) {
        log.info("find location by id {}", id);

        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("location not found"));
    }
}
