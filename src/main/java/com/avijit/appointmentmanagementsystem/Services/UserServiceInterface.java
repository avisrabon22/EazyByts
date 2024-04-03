package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.UserRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;

public interface UserServiceInterface {

    public UserResponseDto userRegister(UserRequestDto userRequestDto);
}
