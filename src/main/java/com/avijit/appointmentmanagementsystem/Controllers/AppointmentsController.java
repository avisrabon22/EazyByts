package com.avijit.appointmentmanagementsystem.Controllers;

import com.avijit.appointmentmanagementsystem.DTO.AppointmentIdDto;
import com.avijit.appointmentmanagementsystem.DTO.UserMailRequestDto;
import com.avijit.appointmentmanagementsystem.Exception.ErrorInServer;
import com.avijit.appointmentmanagementsystem.Services.UserAppointmentListUpdateInterface;
import org.springframework.web.bind.annotation.*;

import com.avijit.appointmentmanagementsystem.Config.Security.JwtAuthentication;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import com.avijit.appointmentmanagementsystem.Services.AppointmentsServiceInterface;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentsController {
    private final AppointmentsServiceInterface appointmentsServiceInterface;
    private final UserAppointmentListUpdateInterface userAppointmentListUpdateInterface;

    public AppointmentsController(AppointmentsServiceInterface appointmentsServiceInterface, UserAppointmentListUpdateInterface userAppointmentListUpdateInterface) {
        this.appointmentsServiceInterface = appointmentsServiceInterface;
        this.userAppointmentListUpdateInterface = userAppointmentListUpdateInterface;
    }

    // Get All Appointments
    @GetMapping("/get_all_appointments")
    public ResponseEntity<List<AppointmentsResponseDto>> getAllAppointments(@CookieValue(name = "Authorization") String token) {
        if (token.startsWith("Bearer")) {
            token = token.substring(7);
            if (JwtAuthentication.validateToken(token)) {
                return new ResponseEntity<>(appointmentsServiceInterface.getAllAppointments(token), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    // Add Appointments
    @PostMapping("/add_appointments")
    public ResponseEntity<String> addAppointments(@RequestBody AppointmentsRequestDto appointmentsRequestDto, @CookieValue(name = "Authorization") String token) throws ErrorInServer {
        if (token.startsWith("Bearer")) {
            token = token.substring(7);
            if (JwtAuthentication.validateToken(token)) {
                AppointmentIdDto idDto = appointmentsServiceInterface.createAppointment(appointmentsRequestDto);
                UserMailRequestDto userMailRequestDto = new UserMailRequestDto();
                String email = JwtAuthentication.extractSubject(token);
                userMailRequestDto.setEmail(email);
                userAppointmentListUpdateInterface.updateUserAppointmentList(userMailRequestDto, idDto.getId());

                return new ResponseEntity<>("Appointment added!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Appointments Not added", HttpStatus.UNAUTHORIZED);

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