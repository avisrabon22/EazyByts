package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.UserRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;

import java.util.Optional;

public interface UserServiceInterface {

    public void userRegister(UserRequestDto userRequestDto);

    public UserResponseDto userLogin(UserRequestDto userRequestDto);

    Optional<UserResponseDto> getProfile();
}
