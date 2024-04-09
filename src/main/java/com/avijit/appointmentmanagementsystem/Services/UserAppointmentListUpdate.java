package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DAO.UserDao;
import com.avijit.appointmentmanagementsystem.DTO.UserMailRequestDto;
import com.avijit.appointmentmanagementsystem.Models.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserAppointmentListUpdate implements UserAppointmentListUpdateInterface {
    private  final UserDao userDao;

    public UserAppointmentListUpdate(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateUserAppointmentList(UserMailRequestDto userMailRequestDto, String id)  {

        Optional<UserModel> userModelOptional = userDao.findByEmail(userMailRequestDto.getEmail());
        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            if (userModel.getAppointmentsIds()==null)
                userModel.setAppointmentsIds(List.of(id));
            else {
                List<String> appointmentsIds = userModel.getAppointmentsIds();
                appointmentsIds.add(id);
                userModel.setAppointmentsIds(appointmentsIds);
            }
            userDao.save(userModel);
        }
        else
            throw new RuntimeException("User not found");
    }
}
