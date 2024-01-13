package com.example.ContentSubscription.Exceptions;

public class NoUserFoundException extends RuntimeException{

    public NoUserFoundException(){
        super("No user with that id was found!");
    }
}
