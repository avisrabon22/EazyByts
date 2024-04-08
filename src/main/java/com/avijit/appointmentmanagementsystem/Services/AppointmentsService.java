package com.avijit.appointmentmanagementsystem.Services;

import java.net.http.HttpRequest;
import java.util.Optional;
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
import jakarta.validation.OverridesAttribute.List;

@Service
public class AppointmentsService implements AppointmentsServiceInterface {
    private final AppointmentDao appointmentDao;
    private final UserDao userDao;

    public AppointmentsService(AppointmentDao appointmentDao, UserDao userDao) {
        this.appointmentDao = appointmentDao;
        this.userDao = userDao;
    }

    @Override
    public void createAppointment(AppointmentsRequestDto appointmentsRequestDto) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto) {
        // TODO Auto-generated method stub

    }

    @Override
    public AppointmentsResponseDto getAllAppointments(AppointmentsRequestDto appointmentsRequestDto,
            HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        Optional<UserModel> user = null;

        for (Cookie cookie : cookies) {
            String token = cookie.getValue();
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
                JwtAuthentication jwtAuthentication = new JwtAuthentication();
                if (jwtAuthentication.validateToken(token)) {
                    String email = JwtAuthentication.extractSubject(token);
                    user = userDao.findById(email);
                } else {
                    return null;
                }
                
            }
        }
        return null;
    }

    @Override
    public AppointmentsResponseDto getAppointment(AppointmentsRequestDto appointmentsRequestDto) {

        return null;
    }

    @Override
    public void updateAppointment(AppointmentsRequestDto appointmentsRequestDto) {
        // TODO Auto-generated method stub

    }

}
