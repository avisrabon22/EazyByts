package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.Config.Security.JwtAuthentication;
import com.avijit.appointmentmanagementsystem.DAO.UserDao;
import com.avijit.appointmentmanagementsystem.DTO.LogInRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.LoginTokenDto;
import com.avijit.appointmentmanagementsystem.DTO.UserRegisterRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResisterResponseDto;
import com.avijit.appointmentmanagementsystem.Exception.NotExist;
import com.avijit.appointmentmanagementsystem.Models.UserModel;
import com.avijit.appointmentmanagementsystem.Models.UserType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.moznion.random.string.RandomStringGenerator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    private final UserDao userDao;
    private final JwtAuthentication jwtAuthentication;

    public UserService(UserDao userDao, JwtAuthentication jwtAuthentication) {
        this.userDao = userDao;
        this.jwtAuthentication = jwtAuthentication;
    }

    // User register method
    @Override
    public void userRegister(UserRegisterRequestDto userRequestDto,HttpServletResponse httpServletResponse) throws IOException, NotExist {
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
//        Set password using bCryptPasswordEncoder
        userModel.setPassword(hashedPassword);
        userModel.setRole(UserType.USER);
        userDao.save(userModel);
        String jwtToken = jwtAuthentication.generateToken(userRequestDto.getEmail());
        String jwtTokenValue = "Bearer "+jwtToken;
        String encodedValue = URLEncoder.encode(jwtTokenValue, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("Authorization", encodedValue);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        httpServletResponse.addCookie(cookie);
    }

    // User login method
    @Override
    public boolean userLogin(LogInRequestDto logInRequestDto, HttpServletResponse httpServletResponse) throws NotExist, IOException {
     String UserEmail = logInRequestDto.getEmail();
        Optional<UserModel> userModel = userDao.findByEmail(UserEmail);
        if (userModel.isEmpty()) {
            throw new NotExist("User not found");
        }
        String password = userModel.get().getPassword();

        if (BCrypt.checkpw(logInRequestDto.getPassword(),password)) {
            String token = jwtAuthentication.generateToken(UserEmail);
            String jwtToken = "Bearer "+token;
            String encodedValue = URLEncoder.encode(jwtToken, StandardCharsets.UTF_8);
            Cookie cookie = new Cookie("Authorization",encodedValue );
            httpServletResponse.addCookie(cookie);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600);
            return true;
        } else {
            throw new NotExist("Password not matched");
        }
    }

    @Override
    public boolean userTokenLogin(LoginTokenDto loginTokenDto) {
        String token = loginTokenDto.getToken();
        if( token.startsWith("Bearer "))
        {
            token = token.substring(7);
        }
           if (jwtAuthentication.validateToken(token)) {
               String email = JwtAuthentication.extractSubject(token);
               System.out.println(email);
               return true;
           }
           else
              {
                return false;
              }
    }


    // Get profile method
    @Override
    public UserResisterResponseDto getProfile(String token) {
//        Cookie[] token = httpServletRequest.getCookies();
        Optional<UserModel> userModel = Optional.empty();
        UserResisterResponseDto userResponseDto = new UserResisterResponseDto();
        if (token != null) {
            if (token.startsWith("Bearer")) {
                token = token.substring(7);
                if (jwtAuthentication.validateToken(token)) {
                    String email = JwtAuthentication.extractSubject(token);
                    userModel = userDao.findByEmail(email);
                }
            }
        }
//       if(token!=null)
//         {
//              for (Cookie cookie : token) {
//                if (cookie.getName().equals("Authorization")) {
//                     String tokenValue = cookie.getValue();
//                     if (tokenValue.startsWith("Bearer "))
//                     {
//                         tokenValue = tokenValue.substring(7);
//                         if(jwtAuthentication.validateToken(tokenValue))
//                         {
//                             String email = JwtAuthentication.extractSubject(tokenValue);
//                             userModel = userDao.findByEmail(email);
//                         }
//                         if (userModel.isEmpty()) {
//                             return null;
//                         }
//                     }
                     if (userModel.isPresent()) {
                         userResponseDto.setName(userModel.get().getName());
                         userResponseDto.setEmail(userModel.get().getEmail());
                         userResponseDto.setRole(userModel.get().getRole());
                     }


        return userResponseDto;
    }

}
