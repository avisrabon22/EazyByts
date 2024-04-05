package com.avijit.appointmentmanagementsystem.Services;

import org.springframework.stereotype.Service;

import com.avijit.appointmentmanagementsystem.DAO.AppointmentDao;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import com.avijit.appointmentmanagementsystem.Models.AppointmentModel;

import jakarta.validation.OverridesAttribute.List;



@Service
public class AppointmentsService implements AppointmentsServiceInterface{
    private final AppointmentDao appointmentDao;

    public AppointmentsService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    @Override
    public void createAppointment(AppointmentsRequestDto appointmentsRequestDto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AppointmentsResponseDto getAllAppointments(AppointmentsRequestDto appointmentsRequestDto) {
            // List<AppointmentModel> appoiList = appointmentDao.findAllById("");
        return null;
        // TODO Auto-generated method stub
        
    }

    @Override
    public AppointmentsResponseDto getAppointment(AppointmentsRequestDto appointmentsRequestDto) {
         
        return null;
    }

    @Override
    public void updateAppointment(AppointmentsRequestDto appointmentsRequestDto) {
        // TODO Auto-generated method stub
        
    }

    
}
