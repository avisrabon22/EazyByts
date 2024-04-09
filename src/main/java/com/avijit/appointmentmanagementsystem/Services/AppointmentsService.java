package com.avijit.appointmentmanagementsystem.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.avijit.appointmentmanagementsystem.DTO.AppointmentIdDto;
import com.avijit.appointmentmanagementsystem.Exception.ErrorInServer;
import org.springframework.stereotype.Service;
import com.avijit.appointmentmanagementsystem.Config.Security.JwtAuthentication;
import com.avijit.appointmentmanagementsystem.DAO.AppointmentDao;
import com.avijit.appointmentmanagementsystem.DAO.UserDao;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsRequestDto;
import com.avijit.appointmentmanagementsystem.DTO.AppointmentsResponseDto;
import com.avijit.appointmentmanagementsystem.Models.AppointmentModel;
import com.avijit.appointmentmanagementsystem.Models.UserModel;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import net.moznion.random.string.RandomStringGenerator;

@Service
public class AppointmentsService implements AppointmentsServiceInterface {
    private final AppointmentDao appointmentDao;
    private final UserDao userDao;

    public AppointmentsService(AppointmentDao appointmentDao, UserDao userDao) {
        this.appointmentDao = appointmentDao;
        this.userDao = userDao;
    }
//  Creating an appointment **************************************************************************************
    @Override
    public AppointmentIdDto createAppointment(AppointmentsRequestDto appointmentsRequestDto) throws ErrorInServer {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        String id = randomStringGenerator.generateByRegex("[a-z0-9]{10}");
        AppointmentModel appointmentModel = new AppointmentModel();
//      Saving the appointment details in the appointment model
        appointmentModel.setId(id);
        appointmentModel.setDate(appointmentsRequestDto.getDate());
        appointmentModel.setTime(appointmentsRequestDto.getTime());
        appointmentModel.setPurpose(appointmentsRequestDto.getPurpose());
        appointmentModel.setLocation(appointmentsRequestDto.getLocation());
        appointmentDao.save(appointmentModel);
        AppointmentIdDto appointmentIdDto = new AppointmentIdDto();
        appointmentIdDto.setId(id);
        return appointmentIdDto;
    }
//  Deleting an appointment **************************************************************************************
    @Override
    public void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto) {

    }
//  Getting all appointments **************************************************************************************
    @Override
    public List<AppointmentsResponseDto> getAllAppointments(String token) {
        String email = JwtAuthentication.extractSubject(token);
        Optional<UserModel> userOptional = userDao.findByEmail(email);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            if (user.getAppointmentsIds() == null) {
                return Collections.emptyList();
            }
            List<String> appointmentsIdList = user.getAppointmentsIds();
            List<AppointmentModel> appointments = appointmentDao.findAllById(appointmentsIdList);
            List<AppointmentsResponseDto> appointmentsResponseDto = new ArrayList<>();
            for (AppointmentModel appointmentModel : appointments) {
                AppointmentsResponseDto appointmentsResponseDto2 = new AppointmentsResponseDto();
                appointmentsResponseDto2.setDate(appointmentModel.getDate());
                appointmentsResponseDto2.setTime(appointmentModel.getTime());
                appointmentsResponseDto2.setPurpose(appointmentModel.getPurpose());
                appointmentsResponseDto2.setLocation(appointmentModel.getLocation());
                appointmentsResponseDto.add(appointmentsResponseDto2);
            }
            return appointmentsResponseDto;
        }
        return Collections.emptyList();
    }

//  Updating an appointment **************************************************************************************
    @Override
    public void updateAppointment(AppointmentsRequestDto appointmentsRequestDto) {

    }

}