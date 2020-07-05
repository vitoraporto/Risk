package com.company;

public class NotEnoughArmiesException extends Exception {
    public NotEnoughArmiesException(String errorMessage){
        super(errorMessage);
    }
}
