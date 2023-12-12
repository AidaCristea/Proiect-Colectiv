package com.example.ContentSubscription.Exceptions;

public class NoSubscriptionTypeFound extends RuntimeException{

    public NoSubscriptionTypeFound(){
        super("No subscription type with that id was found!");
    }
}
