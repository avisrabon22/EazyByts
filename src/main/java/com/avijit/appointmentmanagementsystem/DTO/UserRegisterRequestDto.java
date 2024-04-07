package com.avijit.appointmentmanagementsystem.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto {
    private String name;
    private String email;
    private String password;
}
