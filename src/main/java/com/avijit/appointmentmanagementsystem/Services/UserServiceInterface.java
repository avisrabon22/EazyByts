package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.*;
import com.avijit.appointmentmanagementsystem.Exception.NotExist;
import com.avijit.appointmentmanagementsystem.Exception.UnAuthorized;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface UserServiceInterface {
    public void userRegister(UserRegisterRequestDto userRequestDto,HttpServletResponse httpServletResponse) throws IOException, NotExist;
    public boolean userLogin(LogInRequestDto logInRequestDto) throws NotExist;
    public boolean userTokenLogin(LoginTokenDto loginTokenDto);
    public UserResisterResponseDto getProfile(UserMailRequestDto userMailRequestDto);
}
