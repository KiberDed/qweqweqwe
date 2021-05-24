package com.example.secTr.repos;

import com.example.secTr.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepos extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
