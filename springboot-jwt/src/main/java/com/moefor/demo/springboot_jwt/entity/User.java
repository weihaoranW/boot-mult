package com.moefor.demo.springboot_jwt.entity;

//@Entity
public class User {

    //@Id
    private Integer id;

    //@Column
    private String Username;

    //@Column
    private String Password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
