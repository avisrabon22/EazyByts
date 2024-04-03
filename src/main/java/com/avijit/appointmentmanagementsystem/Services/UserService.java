package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DAO.UserDao;
import com.avijit.appointmentmanagementsystem.DTO.UserRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.UserResponseDto;
import com.avijit.appointmentmanagementsystem.Models.UserModel;
import com.avijit.appointmentmanagementsystem.Models.UserType;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public UserResponseDto userRegister(UserRequestDto userRequestDto) {
        UserModel userModel = new UserModel();

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
}
