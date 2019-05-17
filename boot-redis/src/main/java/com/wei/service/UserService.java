package com.wei.service;

import com.wei.entity.UserTest;

import java.util.List;

public interface UserService {

UserTest findById(Long id);

void delete(UserTest userTest);

List<UserTest> findAll();
}
