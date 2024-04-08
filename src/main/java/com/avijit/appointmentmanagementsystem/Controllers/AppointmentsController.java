package com.avijit.appointmentmanagementsystem.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import com.avijit.appointmentmanagementsystem.Services.AppointmentsServiceInterface;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentsController {
    private final AppointmentsServiceInterface appointmentsServiceInterface;

    public AppointmentsController(AppointmentsServiceInterface appointmentsServiceInterface) {
        this.appointmentsServiceInterface = appointmentsServiceInterface;
    }

    // Set appointments
    @PostMapping("/addAppointments")
    public AppointmentsResponseDto SetAppointments(@RequestBody AppointmentsRequestDto appointmentsRequestDto) {
            
        return appointmentsServiceInterface.getAllAppointments(appointmentsRequestDto);
    }

    // Get appointments
    @GetMapping("/getAppointments")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    // Edit appointments
    @PutMapping("getAppointments/{id}")
    public String editAppointments(@PathVariable String id, @RequestBody String entity) {
        return entity;
    }

    // Delete appointments
    @DeleteMapping("getAppointments/{id}")
    public String deleteAppointments(@PathVariable String id) {
        return id;
    }

}