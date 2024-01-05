package com.example.ContentSubscription.Exceptions;

public class NoCreatorFoundException extends RuntimeException{

    public NoCreatorFoundException()
    {
        super("No creator with the given identifier was found!");
    }
}
