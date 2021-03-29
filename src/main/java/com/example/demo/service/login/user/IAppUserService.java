package com.example.demo.service.login.user;

import com.example.demo.model.login.AppRole;
import com.example.demo.model.login.AppUser;
import com.example.demo.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAppUserService extends IService<AppUser> {
    AppUser getAccountByUserName(String username);
    AppUser getCurrentAccount();
    Page<AppUser> findAll(Pageable pageable);
    List<AppUser> findAllByAppRole(AppRole appRole);
    Page<AppUser> findAllByUsername(String username, Pageable pageable);
    AppUser getCurrentUser();
}
