package com.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class TUser implements Serializable {

private static final long serialVersionUID = 1L;

@TableId(value = "id", type = IdType.AUTO)
private Integer id;

private String name;

private Integer age;

public Integer getId() {
 return id;
}

public void setId(Integer id) {
 this.id = id;
}

public String getName() {
 return name;
}

public void setName(String name) {
 this.name = name;
}

public Integer getAge() {
 return age;
}

public void setAge(Integer age) {
 this.age = age;
}

@Override
public String toString() {
 return "TUser{" +
         "id=" + id +
         ", name=" + name +
         ", age=" + age +
         "}";
}
}
