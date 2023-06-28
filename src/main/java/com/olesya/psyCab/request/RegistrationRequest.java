package com.olesya.psyCab.request;

import com.olesya.psyCab.email.ValidEmail;
import com.olesya.psyCab.entity.Role;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Data
@Getter
@Setter
public class RegistrationRequest {
    //@NotNull
    //, message = "{size.user.username}")
    private String username;

    @ValidEmail
    //@NotNull
    //, message = "{size.user.email}")
    private String email;

    //@NotNull
    @Size(min = 6)
            //message = "{size.user.email}")
    private String password;

    //@NotNull
    @Size(min = 6)
    private String confirmPassword;
    private boolean isEnable;
    private List<Role> roles;
}
