package com.example.newapp.pages.examples.tables;

import org.apache.tapestry5.annotations.Property;

public class AddAndRemoveColumns2 {

    // Screen fields

    @Property
    private String firstName;
    
    // The code
    
    String onPassivate() {
        return firstName;
    }

    void onActivate(String firstName) {
        this.firstName = firstName;
    }
}
