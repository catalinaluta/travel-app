package com.sda.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PropertyController {

    // admin@gmail.com / pass
    // user@gmail.com / pass
    // main@gmail.com / pass
    @GetMapping("/properties")
    public String getPropertiesPage() {
        // TODO: add list of properties
        return "property/properties";
    }
}
