package com.flipkart.exception;

public class CustomerNotFound extends Exception{
    @Override
    public String getMessage(){
        return "User Not Found. Please register or login using different account";
    }
}
