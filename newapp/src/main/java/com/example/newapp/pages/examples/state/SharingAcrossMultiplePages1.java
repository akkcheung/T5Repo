package com.example.newapp.pages.examples.state;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import com.example.newapp.data.ShoppingBasket;
import com.example.newapp.pages.Index;

public class SharingAcrossMultiplePages1 {
// Screen fields
    
    @SessionState
    @Property
    private ShoppingBasket myBasket ;
    
    // The code

    Object onSuccess() {
        return SharingAcrossMultiplePages2.class;
    }

    Object onGoHome() {
        // Delete the SSO from the session
        myBasket = null;       
        return Index.class;
    }
    
    
}
