import  com.avijit.appointmentmanagementsystem.Models.BaseModel;
import  com.avijit.appointmentmanagementsystem.Models.AdminModel;
import  com.avijit.appointmentmanagementsystem.Enums.UserType;
import  jakarta.persistence.Column;
import  jakarta.persistence.Entity;
import  jakarta.persistence.EnumType;
import  jakarta.persistence.Enumerated;
import  lombok.Getter;
import  lombok.Setter;



@Getter
@Setter
@Entity
public class UserMode extends BaseModel{
    // Class variables (attributes)
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Enum<UserType> userType;
   
}