package com.avijit.appointmentmanagementsystem.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/admin")
public class AdminController {

    @PostMapping("/login")
    public String login(@RequestParam String param) {
        return new String();
    }

    // Get all appointments
    @GetMapping("/getAllAppointments")
    public String getAllAppointments(@RequestParam String param) {
        return new String();
    }

    // Get all users
    @GetMapping("/getAllUsers")
    public String getAllUsers(@RequestParam String param) {
        return new String();
    }

}
