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

    void changeEmail(User user, String email);

    void changeUsername(User user, String username);

    void changeFirstName(User user, String firstName);

    void changeLastName(User user, String lastName);

    void changePhoneNumber(User user, Long telephoneNumber);

    boolean checkIfValidOldPassword(User user, String password);

    void changeUserPassword(User user, String password);
}
