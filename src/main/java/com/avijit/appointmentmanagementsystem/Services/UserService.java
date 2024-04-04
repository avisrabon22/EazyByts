package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DAO.UserDao;
import com.avijit.appointmentmanagementsystem.DTO.UserRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;
import com.avijit.appointmentmanagementsystem.Models.UserModel;
import com.avijit.appointmentmanagementsystem.Models.UserType;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{
    private final UserDao userDao;
    private final  BCryptPasswordEncoder bcryptPasswordEncoder;
 
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,UserDao userDao) {
        this.userDao = userDao;
        this.bcryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private String hashpassword(String password){
           return bcryptPasswordEncoder.encode(password);
    }

 
    @Override
    public UserResponseDto userRegister(UserRequestDto userRequestDto) {
        UserModel userModel = new UserModel();

        userModel.setName(userRequestDto.getName());
        userModel.setEmail(userRequestDto.getEmail());
        userModel.setPassword(hashpassword(userRequestDto.getPassword()));
        userModel.setRole(UserType.USER);
        UserModel userResponseDb = userDao.save(userModel);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(userResponseDb.getEmail());
        userResponseDto.setRole(userResponseDb.getRole());

        return userResponseDto;
    }

    private boolean checkpassword(String password,String hashpassword){
        return bcryptPasswordEncoder.matches(password,hashpassword);
    }


    @Override
    public UserResponseDto userLogin(UserRequestDto userRequestDto) {

        UserModel userModel = userDao.findByEmail(userRequestDto.getEmail());
        if(userModel == null){
            throw new RuntimeException("User not found");
        }
        
        if(checkpassword(userRequestDto.getPassword(),userModel.getPassword())){
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setEmail(userModel.getEmail());
            userResponseDto.setRole(userModel.getRole());
            return userResponseDto;
        }
       
        throw new UnsupportedOperationException("Unimplemented method 'userLogin'");
    }

    
}
