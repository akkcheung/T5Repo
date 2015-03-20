package com.example.newapp.pages.examples.state;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;

// import com.example.newapp.pages.Index;



public class PassingByPersist {

	 // Work fields

    @Persist(PersistenceConstants.FLASH)
    private String firstName;

    @Persist(PersistenceConstants.FLASH)
    private String lastName;

    // Generally useful bits and pieces

    @Inject
    private ComponentResources componentResources;

    // The code

    // set() is public so that other pages can use it to set up this page.

    public void set(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    Object onReturn() {
        componentResources.discardPersistentFieldChanges();
        return PassingDataBetweenPages.class;
    }

    Object onGoHome() {
        componentResources.discardPersistentFieldChanges();
        // return Index.class;
        return null;
    }

}
