package com.example.demo.repository;

import com.example.demo.model.login.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser getAppUserByUsername(String username);
    List<AppUser> getAllByOrderByAppRole();

}
