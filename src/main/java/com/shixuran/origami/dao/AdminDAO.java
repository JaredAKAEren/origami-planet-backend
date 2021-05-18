package com.shixuran.origami.dao;

import com.shixuran.origami.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminDAO extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUserId(int id);
}
