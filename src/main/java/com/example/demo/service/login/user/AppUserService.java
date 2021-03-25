package com.example.demo.service.login.user;

import com.example.demo.model.login.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements IAppUserService, UserDetailsService {
    @Autowired
    AppUserRepository appUserRepository;


    @Override
    public List<AppUser> findAll() {
        return appUserRepository.getAllByOrderByAppRole();
    }

    @Override
    public AppUser findById(Long id) {
        return appUserRepository.getOne(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public void remove(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public AppUser getAccountByUserName(String username) {
        return appUserRepository.getAppUserByUsername(username);
    }

    @Override
    public AppUser getCurrentAccount() {
        AppUser appUser;
        String username;
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof UserDetails) {
            username = ((UserDetails) object).getUsername();
        } else {
            username = object.toString();
        }
        appUser = this.getAccountByUserName(username);
        return appUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser userAccount = this.getAccountByUserName(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(userAccount.getAppRole());
        return new User(
                userAccount.getUsername(),
                userAccount.getPassword(),
                authorityList
        );
    }
}
