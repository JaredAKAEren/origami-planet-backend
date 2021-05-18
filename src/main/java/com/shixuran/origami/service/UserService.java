package com.shixuran.origami.service;

import com.shixuran.origami.dao.UserDAO;
import com.shixuran.origami.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User isExist(String username) {
        User user = getByUserName(username);
        if (user == null) {
            return user;
        } else {
            User user1 = new User();
            user1.setId(user.getId());
            user1.setUsername(user.getUsername());
            return user1;
        }
    }

    public User getByUserName(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password) {
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}
