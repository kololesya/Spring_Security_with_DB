package com.olesya.psyCab.service;

import com.olesya.psyCab.request.RegistrationRequest;
import com.olesya.psyCab.entity.User;

import java.util.*;

public interface UserService {
    List<User> getAllUsers();

    User registerUser(RegistrationRequest request);

    User findByEmail(String email);
    Optional<User> findById(Long id);

    void deleteUser(Long id);

    //void updateUser(Long id, String firstName, String lastName, String email);


}
