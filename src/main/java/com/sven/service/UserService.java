package com.sven.service;

import com.sven.dao.UserDao;
import com.sven.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sven on 2018/1/4.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getById(int id){
        return userDao.getById(id);
    }

    //@Transactional
    public boolean ts() {
        User u2 = new User();
        u2.setId(2);
        u2.setName("adafa");
        userDao.insert(u2);
        User u1 = new User();
        u1.setName("222");
        u1.setId(1);
        userDao.insert(u1);

        return true;
    }
}
