package com.example.demo.service.login.role;

import com.example.demo.model.login.AppRole;
import com.example.demo.repository.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppRoleService implements IAppRoleService{
    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public List<AppRole> findAll() {
        return appRoleRepository.getAllByOrderByNameDesc();
    }

    @Override
    public AppRole findById(Long id) {
        return appRoleRepository.getOne(id);
    }

    @Override
    public AppRole save(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void remove(Long id) {
        appRoleRepository.deleteById(id);
    }

    @Override
    public AppRole findByName(String name) {
        return appRoleRepository.findByName(name);
    }
}
