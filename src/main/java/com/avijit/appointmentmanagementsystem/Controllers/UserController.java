package com.avijit.appointmentmanagementsystem.Controllers;

import com.avijit.appointmentmanagementsystem.DTO.UserRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;
import com.avijit.appointmentmanagementsystem.Services.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserServiceInterface userServiceInterface;

    public UserController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    // User registration
    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@Validated @RequestBody UserRequestDto userRequestDto) {
        userServiceInterface.userRegister(userRequestDto);

        return new ResponseEntity<>("User created successfully!!", HttpStatus.CREATED);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> userLogin(@Validated @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userServiceInterface.userLogin(userRequestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // User profile
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getProfile(String email) {
        UserResponseDto responseDto=userServiceInterface.getProfile(email);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    // Edit user profile
    @PutMapping("profile/{id}")
    public String editUser(@PathVariable String id, @RequestBody String entity) {
        return entity;
    }
}
