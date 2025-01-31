package com.example.service;

import com.example.model.User;

public interface UserService {

    User create(User user);

    User getById(long id);

    User update(User user);

    void delete(long id);
}