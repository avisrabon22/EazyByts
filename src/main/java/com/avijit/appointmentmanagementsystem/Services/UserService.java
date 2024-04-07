package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DAO.UserDao;
import com.avijit.appointmentmanagementsystem.DTO.LogInRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserRegisterRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResisterResponseDto;
import com.avijit.appointmentmanagementsystem.Exception.NotExist;
import com.avijit.appointmentmanagementsystem.Models.UserModel;
import com.avijit.appointmentmanagementsystem.Models.UserType;
import net.moznion.random.string.RandomStringGenerator;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // User register method
    @Override
    public void userRegister(UserRegisterRequestDto userRequestDto) {
        UserModel userModel = new UserModel();
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

        userModel.setId(randomStringGenerator.generateByRegex("[a-z0-9]{10}"));
        userModel.setName(userRequestDto.getName());
        userModel.setEmail(userRequestDto.getEmail());
        userModel.setPassword(userRequestDto.getPassword());
        userModel.setRole(UserType.USER);
        userDao.save(userModel); 
   }

    // User login method
    @Override
    public void userLogin(LogInRequestDto logInRequestDto) throws NotExist {

         String email = logInRequestDto.getEmail();
        Optional<UserModel> userModel =  userDao.findByEmail(email);
        //
        if(userModel.isPresent()&&userModel.get().getPassword().equals(logInRequestDto.getPassword())){
            System.out.println("Login success");
        }
        else{
            throw new NotExist("User not found");      
        }
    }

    
    // Get profile method
    @Override
    public UserResisterResponseDto getProfile(String email) {
        System.out.println("getProfile");
        Optional<UserModel> userModel =  userDao.findByEmail("avijit@gmail.com");
        System.out.println(userModel);
        UserResisterResponseDto userResponseDto = new UserResisterResponseDto();
        if (userModel.isPresent()) {
            userResponseDto.setName(userModel.get().getName());
            userResponseDto.setEmail(userModel.get().getEmail());
            userResponseDto.setRole(userModel.get().getRole());
            
        }

        return userResponseDto;
    }

}
