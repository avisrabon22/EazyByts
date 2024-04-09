package com.avijit.appointmentmanagementsystem.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.appointmentmanagementsystem.Config.Security.JwtAuthentication;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import com.avijit.appointmentmanagementsystem.Services.AppointmentsServiceInterface;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentsController {
    private final AppointmentsServiceInterface appointmentsServiceInterface;

    public AppointmentsController(AppointmentsServiceInterface appointmentsServiceInterface) {
        this.appointmentsServiceInterface = appointmentsServiceInterface;
    }

    // Get All Appointments
    @GetMapping("/get_all_appointments")
    public ResponseEntity< List<AppointmentsResponseDto>> getAllAppointments(HttpServletRequest httpServletRequest) { 
        return new ResponseEntity<>(appointmentsServiceInterface.getAllAppointments(httpServletRequest),HttpStatus.OK);
    }
// Add Appointments
@PostMapping("/add_appointments")
public ResponseEntity< String> addAppointments(@RequestBody AppointmentsRequestDto appointmentsRequestDto,HttpServletRequest httpServletRequest) {
    Cookie[] cookies =  httpServletRequest.getCookies();
    for (Cookie cookie : cookies) {
        String token = cookie.getValue();
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
            JwtAuthentication jwtAuthentication = new JwtAuthentication();
            if (jwtAuthentication.validateToken(token)) {
                appointmentsServiceInterface.createAppointment(appointmentsRequestDto);
               return new ResponseEntity<>("Appointment added!",HttpStatus.OK);
            }
        }
    }

    return new ResponseEntity<>("Appointments Not added",HttpStatus.UNAUTHORIZED);

}

    // Edit appointments
    @PutMapping("update_appointments/{id}")
    public String editAppointments(@PathVariable String id, @RequestBody String entity) {
        return entity;
    }

    // Delete appointments
    @DeleteMapping("delete_appointments/{id}")
    public String deleteAppointments(@PathVariable String id) {
        return id;
    }

}