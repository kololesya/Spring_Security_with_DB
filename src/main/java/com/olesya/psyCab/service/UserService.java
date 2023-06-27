package com.olesya.psyCab.service;

import com.olesya.psyCab.registration.RegistrationRequest;
import com.olesya.psyCab.entity.User;

import java.util.*;

public interface UserService {
    List<User> getAllUsers();

    User registerUser(RegistrationRequest request);

    User findByEmail(String email);
}
