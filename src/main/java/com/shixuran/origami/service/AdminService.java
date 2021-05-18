package com.shixuran.origami.service;

import com.shixuran.origami.dao.AdminDAO;
import com.shixuran.origami.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDAO;

    public boolean isExit(int id) {
        Optional<Admin> optionalAdmin = adminDAO.findByUserId(id);
        return optionalAdmin.isPresent();
    }
}
