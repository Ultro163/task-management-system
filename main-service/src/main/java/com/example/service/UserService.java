package com.example.service;

import com.example.model.User;

import java.util.UUID;

public interface UserService {

    User create(User user);

    User getById(UUID id);

    User update(User user);

    void delete(UUID id);
}