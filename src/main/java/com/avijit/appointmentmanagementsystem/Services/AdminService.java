package com.avijit.appointmentmanagementsystem.Services;

import org.springframework.stereotype.Service;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResisterResponseDto;

@Service
public class AdminService implements AdminServiceInterface {
    @Override
    public void createUser(UserResisterResponseDto userResponseDto) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(UserResisterResponseDto userResponseDto) {
        // TODO Auto-generated method stub

    }

    @Override
    public AppointmentsResponseDto getAllAppointments(AppointmentsRequestDto appointmentsRequestDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResisterResponseDto getAllUsers(UserResisterResponseDto userResponseDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateAdmin() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateUser(UserResisterResponseDto userResponseDto) {
        // TODO Auto-generated method stub

    }

}
