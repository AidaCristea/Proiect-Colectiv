package com.example.ContentSubscription.Exceptions;

public class NoPostFoundException extends RuntimeException{
    public NoPostFoundException(){
        super("No post with the given id was found!");
    }
}
