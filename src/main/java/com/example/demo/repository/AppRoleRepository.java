package com.example.demo.repository;

import com.example.demo.model.login.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByName(String name);

}
