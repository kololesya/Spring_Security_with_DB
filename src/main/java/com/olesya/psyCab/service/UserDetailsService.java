package com.olesya.psyCab.service;

import com.olesya.psyCab.repository.UserRepository;
import com.olesya.psyCab.security.UserDetailsSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(UserDetailsSecurity:: new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
