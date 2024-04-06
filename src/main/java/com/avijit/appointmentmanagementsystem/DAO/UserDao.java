package com.avijit.appointmentmanagementsystem.DAO;

import com.avijit.appointmentmanagementsystem.Models.UserModel;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends MongoRepository<UserModel, String> {


   Optional<UserModel> findByEmail(String email);


}
