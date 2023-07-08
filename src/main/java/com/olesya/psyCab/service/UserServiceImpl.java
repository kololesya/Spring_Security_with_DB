package com.olesya.psyCab.service;

import com.olesya.psyCab.repository.RoleRepository;
import com.olesya.psyCab.request.RegistrationRequest;
import com.olesya.psyCab.repository.UserRepository;
import com.olesya.psyCab.entity.Role;
import com.olesya.psyCab.entity.User;

import com.olesya.psyCab.token.VerificationTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    VerificationTokenService verificationTokenService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");

        if (isRoleRepoEmpty()){
            roleRepository.saveAll(List.of(roleUser, roleAdmin));
        } else if (isRoleExist("ROLE_USER")){
            roleRepository.save(roleUser);
        } else if (isRoleExist("ROLE_ADMIN")) {
            roleRepository.save(roleAdmin);
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.isEnable());

        if (request.getUsername().contains("admin")){
            user.setRoles((Set<Role>) roleRepository.findByNameRole("ROLE_ADMIN"));
        } else {
            user.setRoles((Set<Role>) roleRepository.findByNameRole("ROLE_USER"));
        }

        userRepository.save(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        deleteVerificationToken(id);
//        Optional<User> theUser = findById(id);
//        theUser.ifPresent(user -> verificationTokenService.deleteUserToken(user.getUserId()));
        userRepository.deleteById(id);
    }

    private void deleteVerificationToken(Long id){
        findById(id).ifPresent(user -> verificationTokenService.deleteUserToken(user.getUserId()));
    }

    @Override
    public void changeEmail(User user, String email){
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void changeUsername(User user, String username) {
        user.setUsername(username);
        userRepository.save(user);
    }

    @Override
    public void changeFirstName(User user, String firstName) {
        user.setFirstName(firstName);
        userRepository.save(user);
    }

    @Override
    public void changeLastName(User user, String lastName) {
        user.setLastName(lastName);
        userRepository.save(user);
    }

    @Override
    public void changePhoneNumber(User user, Long phoneNumber){
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    private boolean isRoleRepoEmpty(){
        return roleRepository.findAll().isEmpty();
    }

    private boolean isRoleExist(String role){
        return roleRepository.findByNameRole(role) == null;
    }
}
