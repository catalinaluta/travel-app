package com.sda.project.service;

import com.sda.project.controller.exception.ResourceNotFoundException;
import com.sda.project.model.Facility;
import com.sda.project.repository.FacilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService {

    private static final Logger log = LoggerFactory.getLogger(FacilityService.class);

    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<Facility> findAll() {
        log.info("finding all facilities");

        return facilityRepository.findAll();
    }

    public Facility save(Facility facility) {
        log.info("save facility {}", facility);

        return facilityRepository.save(facility);
    }

    public Facility findById(Long id) {
        log.info("find facility by id {}", id);

        return facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("facility not found"));
    }
}
