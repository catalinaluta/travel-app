package com.sda.project.controller;


@Controller
public class UserController {

    @GetMapping (value = "/travel-app")
    public String getHomePage(){
        System.out.println("Travel-app");


        return "travel-app";
}
