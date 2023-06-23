package com.olesya.psyCab.registration;

import com.olesya.psyCab.user.Role;
import lombok.Data;

import java.util.*;

@Data
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;
    private boolean isEnable;
    private List<Role> roles;
}
