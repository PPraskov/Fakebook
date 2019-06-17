package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND ,reason = "User not found!")
public class UserNotFoundException extends RuntimeException {
//    private int statusCode;
//
//    {
//        statusCode = 404;
//    }
//
//    public UserNotFoundException(String message) {
//        super(message);
//        System.out.println();
//    }
//
//    public int getStatusCode() {
//        return this.statusCode;
//    }
}


//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order not found!")
//public class OrderNotFoundException extends RuntimeException {
//
//    private int statusCode;
//
//    public OrderNotFoundException() {
//        this.statusCode = 404;
//    }
//
//    public OrderNotFoundException(String message) {
//        super(message);
//        this.statusCode = 404;
//    }
//
//    public int getStatusCode() {
//        return statusCode;
//    }
//}