package com.olesya.psyCab.repository;

import com.olesya.psyCab.entity.PasswordResetToken;
import com.olesya.psyCab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String theToken);

    //PasswordResetToken findByEmail(String email);

    Optional<PasswordResetToken> findByUser(User user);


//    Stream<PasswordResetToken> findAllByExpirationTimeLessThan(Date now);
//
//    void deleteByExpirationTimeLessThan(Date now);
//
//    @Modifying
//    @Query("delete from PasswordResetToken t where t.expirationTime <= ?1")
//    void deleteAllExpiredSince(Date now);

}
