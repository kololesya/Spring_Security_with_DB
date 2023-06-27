package com.olesya.psyCab.entity;

import java.util.Date;

import com.olesya.psyCab.token.VerificationToken;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private Date expirationTime;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationTime = VerificationToken.getExpirationTime();
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "token='" + token + '\'' +
                ", expirationTime=" + expirationTime +
                ", user=" + user.toString() +
                '}';
    }
}
