package com.avijit.appointmentmanagementsystem.Controllers;

import com.avijit.appointmentmanagementsystem.Config.Security.JwtAuthentication;
import com.avijit.appointmentmanagementsystem.DTO.*;
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

    // User registration ************************************************************************************************
    @PostMapping("/user/register")
    public ResponseEntity<String> userRegister( @RequestBody UserRegisterRequestDto userRequestDto, HttpServletResponse httpServletResponse) throws IOException, NotExist {
        if (userRequestDto.getEmail().isEmpty() || userRequestDto.getName() == null || userRequestDto.getPassword() == null) {
            return new ResponseEntity<>("Please fill all the fields", HttpStatus.BAD_REQUEST);
        }
        userServiceInterface.userRegister(userRequestDto, httpServletResponse);

        return new ResponseEntity<>("User created successfully!!", HttpStatus.CREATED);
    }

    // User login ******************************************************************************************************
    @PostMapping("/user/login")
    public ResponseEntity<String> userLogin(@Validated @RequestBody LogInRequestDto logInRequestDto, HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) throws NotExist, IOException {
        LoginTokenDto loginTokenDto = new LoginTokenDto();
        if (httpServletRequest.getHeader("Authorization") != null) {
            loginTokenDto.setToken(httpServletRequest.getHeader("Authorization"));
        }

        if (loginTokenDto.getToken() != null) {
            if (userServiceInterface.userTokenLogin(loginTokenDto)) {
                return ResponseEntity.ok("Welcome back");
            }
        } else if (userServiceInterface.userLogin(logInRequestDto, httpServletResponse)) {
            return ResponseEntity.ok("User has logged in");
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
    }

    //    User logout *****************************************************************************************************
    @PostMapping("/user/logout")
    public ResponseEntity<String> userLogout(HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("Authorization", "");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        return new ResponseEntity<>("User has logged out", HttpStatus.OK);
    }

    // User profile *****************************************************************************************************
    @GetMapping("/user/profile")
    public ResponseEntity<UserResisterResponseDto> getProfile(@CookieValue(name = "Authorization") String token)  {
        if (token != null) {
            if (token.startsWith("Bearer")) {
                token = token.substring(7);
                if (JwtAuthentication.validateToken(token)) {
                    String email = JwtAuthentication.extractSubject(token);
                    UserMailRequestDto userMailRequestDto = new UserMailRequestDto();
                    userMailRequestDto.setEmail(email);
                    UserResisterResponseDto responseDto = userServiceInterface.getProfile(userMailRequestDto);
                    return new ResponseEntity<>(responseDto, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    // Edit user profile
    @PutMapping("/user/profile/{id}")
    public UserResisterResponseDto editUser(HttpServletResponse httpServletResponse) {
        UserResisterResponseDto entity = new UserResisterResponseDto();
        String token = httpServletResponse.getHeader("Authorization");
        return entity;
    }
}
