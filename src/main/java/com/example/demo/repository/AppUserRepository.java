package com.example.demo.repository;

import com.example.demo.model.login.AppRole;
import com.example.demo.model.login.AppUser;
import com.example.demo.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser getAppUserByUsername(String username);
    List<AppUser> getAllByOrderByAppRoleDesc();
    Page<AppUser> getAllByOrderByAppRoleDesc(Pageable pageable);

    List<AppUser> findAllByAppRole(AppRole appRole);

    List<AppUser> getAllByUsername(String username);

    @Query(value = "select * from app_user where app_user.username like ?", nativeQuery = true)
    Page<AppUser> findUserByUserName(String username, Pageable pageable);

}
