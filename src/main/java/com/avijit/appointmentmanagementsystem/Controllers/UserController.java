package com.avijit.appointmentmanagementsystem.Controllers;

import com.avijit.appointmentmanagementsystem.DTO.UserRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;
import com.avijit.appointmentmanagementsystem.Services.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceInterface UserServiceInterface;

    public UserController(UserServiceInterface userServiceInterface) {
        UserServiceInterface = userServiceInterface;
    }

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@Validated @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = UserServiceInterface.userRegister(userRequestDto);

        return new ResponseEntity<>("User created successfully!!",HttpStatus.CREATED);


    }

}
