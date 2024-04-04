package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;

public interface AdminServiceInterface {
    // Admin related methods
    void updateAdmin();

    // Appointments related methods
    AppointmentsResponseDto getAllAppointments(AppointmentsRequestDto appointmentsRequestDto);

    void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto);

    // User related methods
    UserResponseDto getAllUsers(UserResponseDto userResponseDto);

    void deleteUser(UserResponseDto userResponseDto);

    void updateUser(UserResponseDto userResponseDto);

    void createUser(UserResponseDto userResponseDto);

}
