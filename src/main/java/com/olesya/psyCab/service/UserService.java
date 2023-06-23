package com.olesya.psyCab.service;

import com.olesya.psyCab.registration.RegistrationRequest;
import com.olesya.psyCab.user.User;

import java.util.*;

public interface UserService {
    List<User> getAllUsers();

    User registerUser(RegistrationRequest request);

    User findByEmail(String email);
//    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}
