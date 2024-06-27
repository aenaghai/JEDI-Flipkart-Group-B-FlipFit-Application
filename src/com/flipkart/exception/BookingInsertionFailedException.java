package com.flipkart.exception;

public class BookingInsertionFailedException extends Exception{
    @Override
    public String getMessage(){
        return "Booking insertion failed. Please try again";
    }
}
