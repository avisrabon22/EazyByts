package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;

public interface AppointmentsServiceInterface {
    
    public void createAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public void updateAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public void getAppointment(AppointmentsRequestDto appointmentsRequestDto);
    public void getAllAppointments(AppointmentsRequestDto appointmentsRequestDto);
}
