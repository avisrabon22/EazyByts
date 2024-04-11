package com.avijit.appointmentmanagementsystem.Models;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@Document(collection = "users")
public class UserModel extends BaseModel {
    // Class variables (attributes)
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Email is required")
    @Indexed(unique = true)
    private String email;
    @NotNull(message = "Password is required")
    private String password;
    private UserType role;
    private List<String> appointmentsIds;
}