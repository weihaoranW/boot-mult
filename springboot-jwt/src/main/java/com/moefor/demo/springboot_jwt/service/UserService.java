package com.moefor.demo.springboot_jwt.service;

import com.moefor.demo.springboot_jwt.entity.User;
import com.moefor.demo.springboot_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User add(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(userRepository.save(user)){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByUsername(s);
        if(user == null) throw new UsernameNotFoundException("Username Not Found");
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>());
        return userDetails;
    }
}
