package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.LogInRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.LoginTokenDto;
import com.avijit.appointmentmanagementsystem.DTO.UserRegisterRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResisterResponseDto;
import com.avijit.appointmentmanagementsystem.Exception.NotExist;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface UserServiceInterface {

    public void userRegister(UserRegisterRequestDto userRequestDto,HttpServletResponse httpServletResponse) throws IOException, NotExist;

    public boolean userLogin(LogInRequestDto logInRequestDto, HttpServletResponse httpServletResponse) throws NotExist, IOException;
    public boolean userTokenLogin(LoginTokenDto loginTokenDto);

    public UserResisterResponseDto getProfile(String token);
}
