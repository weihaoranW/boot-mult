package com.wei.service.impl;

import com.wei.entity.UserTest;
import com.wei.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

@Override
public UserTest findById(Long id) {

 return new UserTest("whr", 88, 1L);
}

@Override
public void delete(UserTest userTest) {

}

@Override
public List<UserTest> findAll() {

 List<UserTest> l = new ArrayList<>();
 for (int i = 0; i < 10; i++) {
  l.add(new UserTest("user_" + i, i * 10, new Long(i)));
 }

 return l;
}
}
