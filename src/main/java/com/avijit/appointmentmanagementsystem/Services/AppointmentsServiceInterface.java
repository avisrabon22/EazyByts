package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;

import jakarta.servlet.http.HttpServletRequest;

public interface AppointmentsServiceInterface {
    public void createAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public void updateAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public AppointmentsResponseDto getAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public AppointmentsResponseDto getAllAppointments(AppointmentsRequestDto appointmentsRequestDto,HttpServletRequest httpServletRequest);
}
