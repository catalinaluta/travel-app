package com.sda.project.service;

import com.sda.project.model.Property;
import com.sda.project.model.Reservation;
import com.sda.project.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {


    private static final Logger log = LoggerFactory.getLogger(PropertyService.class);

   private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> findAll() {
        log.info("finding all properties");

        return propertyRepository.findAll();
    }
}
