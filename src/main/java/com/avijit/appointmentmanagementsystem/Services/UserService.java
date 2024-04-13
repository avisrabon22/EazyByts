package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.Config.Security.JwtAuthentication;
import com.avijit.appointmentmanagementsystem.DAO.UserDao;
import com.avijit.appointmentmanagementsystem.DTO.*;
import com.avijit.appointmentmanagementsystem.Exception.NotExist;
import com.avijit.appointmentmanagementsystem.Models.UserModel;
import com.avijit.appointmentmanagementsystem.Models.UserType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import net.moznion.random.string.RandomStringGenerator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // User register method ******************************************************************************************
    @Override
    public void userRegister(UserRegisterRequestDto userRequestDto, HttpServletResponse httpServletResponse) throws IOException, NotExist {
        String email = userRequestDto.getEmail();
        Optional<UserModel> userModelOptional = userDao.findByEmail(email);
        if (userModelOptional.isPresent()) {
            throw new NotExist("User already exists");
        }
        UserModel userModel = new UserModel();
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

        userModel.setId(randomStringGenerator.generateByRegex("[a-z0-9]{10}"));
        userModel.setName(userRequestDto.getName());
        userModel.setEmail(userRequestDto.getEmail());
        String password = userRequestDto.getPassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        userModel.setPassword(hashedPassword);
        userModel.setRole(UserType.USER);
        userDao.save(userModel);
        String jwtToken = JwtAuthentication.generateToken(userRequestDto.getEmail());
        String jwtTokenValue = "Bearer " + jwtToken;
        String encodedValue = URLEncoder.encode(jwtTokenValue, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("Authorization", encodedValue);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    // User login method ********************************************************************************************
    @Override
    public boolean userLogin(LogInRequestDto logInRequestDto) throws NotExist{
        String UserEmail = logInRequestDto.getUsername();
        Optional<UserModel> userModel = userDao.findByEmail(UserEmail);
        if (userModel.isEmpty()) {
            throw new NotExist("User not found");
        }
        String password = userModel.get().getPassword();
        if (BCrypt.checkpw(logInRequestDto.getPassword(), password)) {
            return true;
        } else {
            throw new NotExist("Password not matched");
        }
    }

    // User login with token method**********************************************************************************
    @Override
    public boolean userTokenLogin(LoginTokenDto loginTokenDto) {
        String token = loginTokenDto.getToken();
        if (token.startsWith("Bearer")) {
            token = token.substring(7);
        }
        if (JwtAuthentication.validateToken(token)) {
            String email = JwtAuthentication.extractSubject(token);
            System.out.println(email);
            return true;
        } else {
            return false;
        }
    }


    // Get profile method********************************************************************************************
    @Override
    public UserResisterResponseDto getProfile(UserMailRequestDto userMailRequestDto) {
        UserResisterResponseDto userResponseDto = new UserResisterResponseDto();
        Optional<UserModel> userModel = userDao.findByEmail(userMailRequestDto.getEmail());

        if (userModel.isPresent()) {
            userResponseDto.setName(userModel.get().getName());
            userResponseDto.setEmail(userModel.get().getEmail());
            userResponseDto.setRole(userModel.get().getRole());
        }

        return userResponseDto;
    }



//    end of the class**********************************************************************************************************************
}