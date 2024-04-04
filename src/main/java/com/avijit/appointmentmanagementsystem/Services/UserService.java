package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DAO.UserDao;
import com.avijit.appointmentmanagementsystem.DTO.UserRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;
import com.avijit.appointmentmanagementsystem.Models.UserModel;
import com.avijit.appointmentmanagementsystem.Models.UserType;
import net.moznion.random.string.RandomStringGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{
    private final UserDao userDao;

 
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


//   User register method
    @Override
    public UserResponseDto userRegister(UserRequestDto userRequestDto) {
        UserModel userModel = new UserModel();
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

        userModel.setId(randomStringGenerator.generateByRegex("[a-z0-9]{10}"));
        userModel.setName(userRequestDto.getName());
        userModel.setEmail(userRequestDto.getEmail());
        userModel.setPassword(userRequestDto.getPassword());
        userModel.setRole(UserType.USER);
        UserModel userResponseDb = userDao.save(userModel);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(userResponseDb.getEmail());
        userResponseDto.setRole(userResponseDb.getRole());

        return userResponseDto;
    }

//User login method
    @Override
    public UserResponseDto userLogin(UserRequestDto userRequestDto) {

        UserModel userModel = userDao.findByEmail(userRequestDto.getEmail());
        if(userModel == null){
            throw new RuntimeException("User not found");
        }
        
        if(userModel.getPassword().equals(userRequestDto.getPassword())){
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setEmail(userModel.getEmail());
            userResponseDto.setRole(userModel.getRole());
            return userResponseDto;
        }
       
        throw new UnsupportedOperationException("Unimplemented method 'userLogin'");
    }

    
}
