package com.avijit.appointmentmanagementsystem.Services;

import java.util.List;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AppointmentsServiceInterface {
    public void createAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public void updateAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public List<AppointmentsResponseDto> getAllAppointments(HttpServletRequest httpServletRequest);
}
