package com.olesya.psyCab.security;

import com.olesya.psyCab.entity.Role;
import com.olesya.psyCab.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.Collection;

@Data
public class ProjectUserDetails implements UserDetails {

    private User user;

    public ProjectUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNameRole()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getUserEmail() {
        return user.getEmail();
    }
    public Long getUserId() {
        return this.user.getUserId();
    }

    public String getUserFirstName() {
        return this.user.getFirstName();
    }

    public String getUserLastName() {
        return this.user.getLastName();
    }

    public Long getUserPhoneNumber() {
        return this.user.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
