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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        } else if (isRoleExist(roleUser)){
            roleRepository.save(roleUser);
        } else {
            roleRepository.save(roleAdmin);
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.isEnable());

        if (request.getUsername().contains("admin")){
            user.setRoles(Arrays.asList(roleRepository.findByNameRole("ROLE_ADMIN")));
        } else {
            user.setRoles(Arrays.asList(roleRepository.findByNameRole("ROLE_USER")));
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
        Optional<User> theUser = findById(id);
        theUser.ifPresent(user -> verificationTokenService.deleteUserToken(user.getUserId()));
        userRepository.deleteById(id);
    }

//    @Transactional
//    @Override
//    public void updateUser(Long id, String firstName, String lastName, String email) {
//        userRepository.update(firstName, lastName, email, id);
//    }

    private boolean isRoleRepoEmpty(){

        return roleRepository.findAll().isEmpty();
    }

    private boolean isRoleExist(Role role){
        return roleRepository.findByNameRole(String.valueOf(role)) == null;
    }
}
