package com.avijit.appointmentmanagementsystem.DTO;
import com.avijit.appointmentmanagementsystem.Models.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private UserType role;
}
