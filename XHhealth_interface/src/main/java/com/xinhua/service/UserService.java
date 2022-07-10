package com.xinhua.service;

import com.xinhua.pojo.User;

public interface UserService {
    public User findByUsername(String username);
}
