package com.avijit.appointmentmanagementsystem.Services;

import com.avijit.appointmentmanagementsystem.DTO.UserMailRequestDto;

public interface UserAppointmentListUpdateInterface {
    public void updateUserAppointmentList(UserMailRequestDto userMailRequestDto, String id);
}
