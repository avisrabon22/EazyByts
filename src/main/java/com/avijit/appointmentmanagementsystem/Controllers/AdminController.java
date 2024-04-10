package com.avijit.appointmentmanagementsystem.Controllers;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/admin")
public class AdminController {

    @PostMapping("/super_login")
    public String login(@RequestBody String param) {
        return new String();
    }

    // Get all appointments
    @GetMapping("/get_All_Appointments")
    public String getAllAppointments(@RequestParam String param) {
        return new String();
    }

    // Get all users
    @GetMapping("/get_All_Users")
    public String getAllUsers(@RequestParam String param) {
        return new String();
    }

}
