
package com.example.demo.config;

import com.example.demo.service.login.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    IAppUserService appUserService;

    @Autowired
    CustomizeSuccessHandle customizeSuccessHandle;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) appUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll().and().
                authorizeRequests().antMatchers("/categories/**").permitAll().and().
                authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and().
                authorizeRequests().antMatchers("/products/manager").hasRole("ADMIN").and().
                authorizeRequests().antMatchers("/products/create").hasRole("ADMIN").and().
                authorizeRequests().antMatchers("/products/edit/{id}").hasRole("ADMIN").and().
                authorizeRequests().antMatchers("/products/delete/{id}").hasRole("ADMIN").and().
                authorizeRequests().antMatchers("/products/**").permitAll().and().
                formLogin().successHandler(customizeSuccessHandle).loginPage("/login").permitAll().and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
    }
}
