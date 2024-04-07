package com.avijit.appointmentmanagementsystem.Controllers;

import com.avijit.appointmentmanagementsystem.DTO.LogInRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserRegisterRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResisterResponseDto;
import com.avijit.appointmentmanagementsystem.Exception.NotExist;
import com.avijit.appointmentmanagementsystem.Services.UserServiceInterface;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserServiceInterface userServiceInterface;

    public UserController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    // @GetMapping()
    // public void redirect(HttpServletResponse response) throws IOException {
    // response.sendRedirect("/swagger-ui.html");
    // }
    // User registration
    @PostMapping("/user/register")
    public ResponseEntity<String> userRegister(@Validated @RequestBody UserRegisterRequestDto userRequestDto) {
        userServiceInterface.userRegister(userRequestDto);

        return new ResponseEntity<>("User created successfully!!", HttpStatus.CREATED);
    }

    // User login
    @PostMapping("/user/login")
    public ResponseEntity<String> userLogin(@Validated @RequestBody LogInRequestDto logInRequestDto) throws NotExist {
        userServiceInterface.userLogin(logInRequestDto);

        return new ResponseEntity<>("User has logged in", HttpStatus.OK);
    }

    // User profile
    @GetMapping("/user/profile")
    public ResponseEntity<UserResisterResponseDto> getProfile(String email) {
        UserResisterResponseDto responseDto = userServiceInterface.getProfile(email);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // Edit user profile
    @PutMapping("/user/profile/{id}")
    public String editUser(@PathVariable String id, @RequestBody String entity) {
        return entity;
    }
}
