package com.avijit.appointmentmanagementsystem.Controllers;

import com.avijit.appointmentmanagementsystem.DTO.UserRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;
import com.avijit.appointmentmanagementsystem.Services.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserServiceInterface UserServiceInterface;
    public UserController(UserServiceInterface userServiceInterface) {
        UserServiceInterface = userServiceInterface;
    }

    // User registration
    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@Validated @RequestBody UserRequestDto userRequestDto) {
        UserServiceInterface.userRegister(userRequestDto);

        return new ResponseEntity<>("User created successfully!!",HttpStatus.CREATED);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> userLogin(@Validated @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = UserServiceInterface.userLogin(userRequestDto);

        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    // User profile
    @GetMapping("/profile")
    public String getProfile(@RequestParam String param) {
        return new String();
    }

    // Edit user profile
    @PutMapping("profile/{id}")
    public String editUser(@PathVariable String id, @RequestBody String entity) {
                
        return entity;
    }
}
