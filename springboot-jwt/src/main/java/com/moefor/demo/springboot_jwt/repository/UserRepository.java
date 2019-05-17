package com.moefor.demo.springboot_jwt.repository;

import com.moefor.demo.springboot_jwt.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {
    /**
     *
     */
    private final ConcurrentMap<Integer, User> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger();

    public boolean save(User user){
        int id = idGenerator.getAndIncrement();
        user.setId(id);
        return repository.put(id, user) == null;
    }

    public User findByUsername(String username){
        Iterator<User> userIterator = repository.values().iterator();
        User user = null;
        while(userIterator.hasNext()){
            user = userIterator.next();
            if(username.equals(user.getUsername())) break;
        }
        return user;
    }
}
