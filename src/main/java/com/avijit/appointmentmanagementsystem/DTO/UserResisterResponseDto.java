package com.avijit.appointmentmanagementsystem.DTO;

import com.avijit.appointmentmanagementsystem.Models.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResisterResponseDto {
    private String name;
    private String email;
    private UserType role;

}
