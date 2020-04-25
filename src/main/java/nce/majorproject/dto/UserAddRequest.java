package nce.majorproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class UserAddRequest {
    @NotBlank(message = "Full Name cannot be blank!!")
    private String fullName;

    @NotBlank(message = "User Name cannot be blank!!")
    private String userName;

    @NotBlank(message = "Address cannot be blank!!")
    private String address;

    private LocalDate dob;

    @Size(min =8,max = 50,message = "invalid length!!")
    @NotBlank(message = "Password cannot be blank!!")
    private String password;

    @NotBlank(message = "Email cannot be blank!!")
    private String email;

    @Size(min = 10,max = 10,message = "invalid length!!")
    @NotBlank(message = "Phone cannot be blank!!")
    private String phone;

}
