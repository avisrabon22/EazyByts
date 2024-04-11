package com.avijit.appointmentmanagementsystem.Models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Setter
@Getter
@Document(collation = "admins")
public class AdminModel extends BaseModel {
     // Class variables (attributes)
    @NotNull
    private String name;
    @NotNull
    @Indexed(unique = true)
    private String email;
    @NotNull
    private String password;
    private Enum<UserType> role;
    private List<UserModel> userListIds;
    private List<AppointmentModel> appointmentsListIds;
}