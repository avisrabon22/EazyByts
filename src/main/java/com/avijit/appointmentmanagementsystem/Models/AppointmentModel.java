package com.avijit.appointmentmanagementsystem.Models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Document(collation = "appointments")
public class AppointmentModel extends BaseModel {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate Date;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH-mm-ss")
    private LocalTime Time;
    @NotNull
    private String Purpose;
    private String Location;
}
