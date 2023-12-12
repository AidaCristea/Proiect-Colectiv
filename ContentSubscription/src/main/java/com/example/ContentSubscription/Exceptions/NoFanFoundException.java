package com.example.ContentSubscription.Exceptions;

public class NoFanFoundException extends RuntimeException{

    public NoFanFoundException(){
        super("No fan with the given identifier was found!");
    }
}
