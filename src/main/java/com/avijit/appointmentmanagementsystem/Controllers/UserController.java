package com.avijit.appointmentmanagementsystem.Controllers;

import com.avijit.appointmentmanagementsystem.DTO.LogInRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.LoginTokenDto;
import com.avijit.appointmentmanagementsystem.DTO.UserRegisterRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResisterResponseDto;
import com.avijit.appointmentmanagementsystem.Exception.NotExist;
import com.avijit.appointmentmanagementsystem.Services.UserServiceInterface;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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


    // User registration
    @PostMapping("/user/register")
    public ResponseEntity<String> userRegister(@Validated @RequestBody UserRegisterRequestDto userRequestDto,HttpServletResponse httpServletResponse) throws IOException, NotExist {
        userServiceInterface.userRegister(userRequestDto,httpServletResponse);

        return new ResponseEntity<>("User created successfully!!", HttpStatus.CREATED);
    }

    // User login
    @PostMapping("/user/login")
    public ResponseEntity<String> userLogin(@Validated @RequestBody LogInRequestDto logInRequestDto,HttpServletResponse httpServletResponse) throws NotExist, IOException {
        LoginTokenDto loginTokenDto = new LoginTokenDto();
        loginTokenDto.setToken(httpServletResponse.getHeader("Authorization"));
        if (loginTokenDto.getToken() != null) {
            if (userServiceInterface.userTokenLogin(loginTokenDto)) {
                return new ResponseEntity<>("Welcome back", HttpStatus.OK);
            }
        } else if (userServiceInterface.userLogin(logInRequestDto, httpServletResponse)) {
            return new ResponseEntity<>("User has logged in", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        return null;
    }

//    User logout
    @PostMapping("/user/logout")
    public ResponseEntity<String> userLogout(HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("Authorization", "");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        return new ResponseEntity<>("User has logged out", HttpStatus.OK);
    }

    // User profile
    @GetMapping("/user/profile")
    public ResponseEntity<UserResisterResponseDto> getProfile(@CookieValue(name = "Authorization") String token) {
//        httpServletRequest.setAttribute("Authorization", token);
        UserResisterResponseDto responseDto = userServiceInterface.getProfile(token);
        if (responseDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // Edit user profile
    @PutMapping("/user/profile/{id}")
    public UserResisterResponseDto editUser(HttpServletResponse httpServletResponse) {
        UserResisterResponseDto entity = new UserResisterResponseDto();
         String token = httpServletResponse.getHeader("Authorization");
        return entity;
    }
}
