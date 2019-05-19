package com.wei.util;

import lombok.Data;

@Data
public class Result<T> {
static final int OK = 0;
static final int ERROR = 1;

private int code;
private String msg;
private T data = null;

public Result(int code, String message, T data) {
 this.code = code;
 this.msg = message;
 this.data = data;
}

public static Result<Long> autoLong(Long l) {
 if (l == null) {
  return err(l);
 }

 return ok(l);
}

public static <TT extends Object> Result autoObj(TT obj) {
 if (obj == null) {
  return err(obj);
 }

 //
 return ok(obj);
}

public static <TT> Result ok(TT data) {
 return new Result<>(OK, "success", data);
}

public static <TT> Result err(TT data) {
 return new Result<>(ERROR, "error", data);
}

@Override
public String toString() {
 return "{\"code\":" + code + ",\"msg\":\"" + msg + "\",\"data\":" + data + "}";
}
}
