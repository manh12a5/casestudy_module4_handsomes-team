package com.example.demo.service.login.user;

import com.example.demo.model.login.AppUser;
import com.example.demo.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAppUserService extends IService<AppUser> {
    AppUser getAccountByUserName(String username);
    AppUser getCurrentAccount();
    Page<AppUser> findAll(Pageable pageable);
}
