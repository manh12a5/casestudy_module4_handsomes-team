package com.example.demo.service.login.user;

import com.example.demo.model.login.AppUser;
import com.example.demo.service.IService;

public interface IAppUserService extends IService<AppUser> {
    AppUser getAccountByUserName(String username);
    AppUser getCurrentAccount();
    AppUser getCurrentUser();
}
