package com.avijit.appointmentmanagementsystem.Services;

import java.util.List;

import com.avijit.appointmentmanagementsystem.DTO.AppointmentIdDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import com.avijit.appointmentmanagementsystem.Exception.ErrorInServer;
import jakarta.servlet.http.HttpServletRequest;

public interface AppointmentsServiceInterface {
    public AppointmentIdDto createAppointment(AppointmentsRequestDto appointmentsRequestDto) throws ErrorInServer;
    public void updateAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public List<AppointmentsResponseDto> getAllAppointments(String token);
}
