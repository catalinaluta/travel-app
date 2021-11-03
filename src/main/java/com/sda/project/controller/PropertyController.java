package com.sda.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PropertyController {

    @GetMapping("/properties")
    public String getPropertiesPage() {
        return "property/properties";
    }
}
