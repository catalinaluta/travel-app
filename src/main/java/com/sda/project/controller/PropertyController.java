package com.sda.project.controller;

import com.sda.project.model.Property;
import com.sda.project.service.PropertyService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PropertyController {

    private final PropertyService propertyService;
    private final UserService userService;

    @Autowired
    public PropertyController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @GetMapping("/properties")
    public String getPropertiesPage(Model model) {
        // get list of properties //1
        List<Property> properties = propertyService.findAll();

        // add to model//2
        model.addAttribute("properties", properties);
        return "property/properties";
    }

    @GetMapping("/properties/add")
    public String showAddPage(Model model) {
        model.addAttribute("property", new Property());
        return "property/property-add";
    }
}
