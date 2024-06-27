package com.flipkart.exception;

public class BookingFailedException extends Exception{
    @Override
    public String getMessage(){
        return "Booking failed. Please try again";
    }
}
