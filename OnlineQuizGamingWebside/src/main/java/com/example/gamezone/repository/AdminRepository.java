package com.example.gamezone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gamezone.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);

}