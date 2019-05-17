package com.wei.util;

import org.springframework.http.HttpStatus;

public class RestResponse<T> {
private int code;

private String message;

private T data = null;

/**
 * Instantiates a new Rest response.
 *
 * @param code    the code
 * @param message the message
 * @param data    the data
 */
public RestResponse(int code, String message, T data) {
 this.code = code;
 this.message = message;
 this.data = data;
}

public RestResponse(HttpStatus status, String message, T data) {
 this(status.value(), message, data);
}

/**
 * Instantiates a new Rest response.
 *
 * @param status the status
 */
public RestResponse(HttpStatus status, T data) {
 this(status.value(), status.getReasonPhrase(), data);
}

/**
 * Instantiates a new Rest response.
 *
 * @param data the data
 */
public RestResponse(T data) {
 this(HttpStatus.OK.value(), "OK", data);
}

public RestResponse(String message, T data) {
 this(HttpStatus.OK.value(), message, data);
}

/**
 * Gets data.
 *
 * @return the data
 */
public T getData() {
 return data;
}

/**
 * Sets data.
 *
 * @param data the data
 */
public void setData(T data) {
 this.data = data;
}

/**
 * Gets code.
 *
 * @return the code
 */
public int getCode() {
 return code;
}

/**
 * Sets code.
 *
 * @param code the code
 */
public void setCode(int code) {
 this.code = code;
}

/**
 * Gets message.
 *
 * @return the message
 */
public String getMessage() {
 return message;
}

/**
 * Sets message.
 *
 * @param message the message
 */
public void setMessage(String message) {
 this.message = message;
}

@Override
public String toString() {
 return "{\"code\":" + code + ",\"message\":\"" + message + "\",\"data\":" + data + "}";
}
}
