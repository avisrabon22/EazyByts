package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.UserRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;

public interface UserServiceInterface {

    public void userRegister(UserRequestDto userRequestDto);

    public UserResponseDto userLogin(UserRequestDto userRequestDto);

    public UserResponseDto getProfile(String email);
}
