package com.avijit.appointmentmanagementsystem.Models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Document(collation = "appointments")
public class AppointmentModel extends BaseModel {
    @NotNull
    private LocalDate Date;
    @NotNull
    private LocalTime Time;
    @NotNull
    private String Purpose;
    private String Location;
}
