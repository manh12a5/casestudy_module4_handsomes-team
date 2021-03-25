package com.example.demo.service.login.role;

import com.example.demo.model.login.AppRole;
import com.example.demo.service.IService;

public interface IAppRoleService extends IService<AppRole> {
    AppRole findByName(String name);
}
