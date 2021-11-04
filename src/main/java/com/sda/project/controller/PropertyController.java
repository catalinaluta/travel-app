package com.sda.project.controller;

import com.sda.project.model.Property;
import com.sda.project.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/properties")
    public String getPropertiesPage(Model model) {
        // get list of properties
        List<Property> properties = propertyService.findAll();

        // add to model
        model.addAttribute("properties", properties);
        return "property/properties";
    }
}
