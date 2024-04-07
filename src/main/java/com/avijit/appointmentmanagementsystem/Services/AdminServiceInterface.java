package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResisterResponseDto;

public interface AdminServiceInterface {
    // Admin related methods
    void updateAdmin();

    // Appointments related methods
    AppointmentsResponseDto getAllAppointments(AppointmentsRequestDto appointmentsRequestDto);

    void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto);

    // User related methods
    UserResisterResponseDto getAllUsers(UserResisterResponseDto userResponseDto);

    void deleteUser(UserResisterResponseDto userResponseDto);

    void updateUser(UserResisterResponseDto userResponseDto);

    void createUser(UserResisterResponseDto userResponseDto);

}
