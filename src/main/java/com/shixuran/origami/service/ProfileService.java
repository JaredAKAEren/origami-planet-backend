package com.shixuran.origami.service;

import com.shixuran.origami.dao.ProfileDAO;
import com.shixuran.origami.pojo.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProfileService {

    @Autowired
    ProfileDAO profileDAO;

    public int saveOrUpdate(Profile profile) {
        try {
            profile.setProfileDate(new Date());
            Profile profileInDB = profileDAO.save(profile);
            return profileInDB.getId();
        } catch (Exception e) {
            return 0;
        }
    }

    public Profile findByUserId(int id) {
        try {
            return profileDAO.findByProfileUserId(id);
        } catch (Exception e) {
            return new Profile();
        }
    }
}
