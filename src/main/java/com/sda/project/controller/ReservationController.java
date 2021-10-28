package com.sda.project.controller;


import com.sda.project.model.Reservation;
import com.sda.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {

    // TODO: implement controller like ProjectController


    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationService reservationService;
    private final UserService userService;

    @Autowired
    public ReservationController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/reservations")
    public String showReservationPage(Model model) {
        model.addAttribute("reservations", projectService.findAll());
        return "reservation/reservations";
    }

    @GetMapping("/reservations/add")
    public String showAddForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("users", userService.findAll());
        return "reservation/reservation-add";
    }

    @PostMapping("/reservations/add")
    public String add(Model model, @ModelAttribute Reservation reservation) {
        try {
            projectService.save(reservation);
            return "redirect:/reservations";
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("reservation", reservation);
            model.addAttribute("users", userService.findAll());
            return "reservation/reservation-add";
        }
    }

    @GetMapping("/reservations/{id}/edit")
    public String showEditForm(Model model, @PathVariable Long id) {
        model.addAttribute("project", projectService.findById(id));
        model.addAttribute("users", userService.findAll());
        return "reservation/reservation-edit";
    }

    @PostMapping("/reservations/{id}/edit")
    public String edit(
            Model model,
            @PathVariable Long id,
            @ModelAttribute Reservation project) {
        try {
            projectService.update(project);
            return "redirect:/reservation";
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("users", userService.findAll());
            return "reservation/reservation-edit";
        }
    }

    @GetMapping("/reservations/{id}/delete")
    public String delete(Model model, @PathVariable Long id) {
        try {
            projectService.delete(id);
            return "redirect:/reservation";
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "reservation/reservations";
        }
    }
}

