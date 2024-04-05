package com.avijit.appointmentmanagementsystem.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.avijit.appointmentmanagementsystem.Models.AppointmentModel;

public interface AppointmentDao extends MongoRepository<AppointmentModel,String> {
    
}
