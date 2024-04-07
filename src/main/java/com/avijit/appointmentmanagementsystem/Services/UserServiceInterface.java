package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.LogInRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserRegisterRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResisterResponseDto;
import com.avijit.appointmentmanagementsystem.Exception.NotExist;

public interface UserServiceInterface {

    public void userRegister(UserRegisterRequestDto userRequestDto);

    public void userLogin(LogInRequestDto logInRequestDto) throws NotExist;

    public UserResisterResponseDto getProfile(String email);
}
