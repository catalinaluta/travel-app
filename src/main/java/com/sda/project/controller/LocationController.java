package com.sda.project.controller;

import com.sda.project.model.Location;
import com.sda.project.service.LocationService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LocationController {

    private final LocationService locationService;
    private final UserService userService;

    @Autowired
    public LocationController(LocationService locationService, UserService userService) {
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("/locations")
    public String getLocationsPage(Model model) {
        // get list of locations
        List<Location> locations = locationService.findAll();

        // add to model
        model.addAttribute("locations", locations);
        return "location/locations";
    }

    @GetMapping("/locations/add")
    public String showAddPage(Model model) {
        model.addAttribute("location", new Location());
        return "location/location-add";
    }

    @PostMapping("/locations/add")
    public String add(@ModelAttribute Location location) {
        locationService.save(location);
        return "redirect:/locations";
    }

    @GetMapping("/locations/{id}")
    public String showLocationInfo(Model model, @PathVariable Long id) {
        // load location by id on model
        model.addAttribute("location", locationService.findById(id));

        return "location/location-info";
    }
}
