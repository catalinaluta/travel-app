package com.sda.project.controller;

import com.sda.project.model.Reservation;
import com.sda.project.service.LocationService;
import com.sda.project.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {

    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationService reservationService;
    private final LocationService locationService;

    @Autowired
    public ReservationController(ReservationService reservationService, LocationService locationService) {
        this.reservationService = reservationService;
        this.locationService = locationService;
    }

    @GetMapping("/reservations")
    public String showReservationPage(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "reservation/reservations";
    }

    @GetMapping("/locations/{id}/reservations/add")
    public String showAddForm(Model model, @PathVariable Long id) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("rooms", locationService.findById(id).getRooms());
        return "reservation/reservation-add";
    }

    @PostMapping("/reservations/add")
    public String add(Model model, @ModelAttribute Reservation reservation) {
        reservationService.save(reservation);
        return "redirect:/reservations";
    }
}

