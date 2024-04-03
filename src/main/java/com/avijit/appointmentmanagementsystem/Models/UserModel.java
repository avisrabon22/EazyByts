package com.avijit.appointmentmanagementsystem.Models;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@Document(collation = "users")
public class UserModel extends BaseModel {
    // Class variables (attributes)
    @NotNull
    private String name;
    @NotNull
    @Indexed(unique = true)
    private String email;
    @NotNull
    private String password;
    private UserType role;
    @DBRef
    private List<AppointmentModel> appointments;


}