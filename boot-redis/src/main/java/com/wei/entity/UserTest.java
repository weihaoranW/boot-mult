package com.wei.entity;

import java.io.Serializable;

public class UserTest implements Serializable {

public UserTest() {

}

public UserTest(String name, Integer age, Long id) {

 this.name = name;
 this.age = age;
 this.id = 0L;
}

private Long id;
private String name;
private Integer age;

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

public Long getId() {

 return id;
}

public void setId(Long id) {

 this.id = id;
}

}
