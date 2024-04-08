package com.avijit.appointmentmanagementsystem.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AppointmentsRequestDto {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private String purpose;
    private String location;
    
}
