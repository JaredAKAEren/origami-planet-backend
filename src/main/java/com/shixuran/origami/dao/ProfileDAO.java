package com.shixuran.origami.dao;

import com.shixuran.origami.pojo.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDAO extends JpaRepository<Profile, Integer> {
    Profile findByProfileUserId(int id);
}
