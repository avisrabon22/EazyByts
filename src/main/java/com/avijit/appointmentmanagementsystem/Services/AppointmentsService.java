package com.avijit.appointmentmanagementsystem.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import net.moznion.random.string.RandomStringGenerator;

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
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        String id = randomStringGenerator.generateByRegex("[a-z0-9]{10}");
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setId(id);
        appointmentModel.setDate(appointmentsRequestDto.getDate());
        appointmentModel.setTime(appointmentsRequestDto.getTime());
        appointmentModel.setPurpose(appointmentsRequestDto.getPurpose());
        appointmentModel.setLocation(appointmentsRequestDto.getLocation());
        appointmentDao.save(appointmentModel);

    }

    @Override
    public void deleteAppointment(AppointmentsRequestDto appointmentsRequestDto) {
     
    }

    @Override
    public List<AppointmentsResponseDto> getAllAppointments(
            HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        
        for (Cookie cookie : cookies) {
            String token = cookie.getValue();
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
                JwtAuthentication jwtAuthentication = new JwtAuthentication();
                if (jwtAuthentication.validateToken(token)) {
                    Optional<UserModel> userOptional = null;
                    String email = JwtAuthentication.extractSubject(token);
                    userOptional = userDao.findById(email);
                    if(userOptional.isPresent()){
                        UserModel user = userOptional.get();
                       java.util.List<String> appointmentsIdList = user.getAppointmentsIds();
                       java.util.List<AppointmentModel> appointments = appointmentDao.findAllById(appointmentsIdList);
                          java.util.List<AppointmentsResponseDto> appointmentsResponseDto = new ArrayList<>();
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
                } else {
                    return Collections.emptyList();
                }
                
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void updateAppointment(AppointmentsRequestDto appointmentsRequestDto) {

    }

}