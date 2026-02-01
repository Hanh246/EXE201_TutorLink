package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.main.entity.RefreshToken;
import com.exe201.tutorlink.main.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(Users user);
}
