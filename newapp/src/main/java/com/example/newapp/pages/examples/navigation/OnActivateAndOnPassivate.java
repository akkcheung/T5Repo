package com.example.newapp.pages.examples.navigation;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;

public class OnActivateAndOnPassivate {
	  // The activation context

    private Long personId;

    // Screen fields

    @Property
    private Person person;

    // Generally useful bits and pieces

    //@EJB
    @Inject
    private IPersonFinderServiceLocal personFinderService;

    // The code

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.

    Long onPassivate() {
        return personId;
    }

    // onActivate() is called by Tapestry to pass in the activation context from the URL.

    void onActivate(Long personId) {
        this.personId = personId;
    }

    // setupRender() is called by tapestry at the start of rendering - it's good for things that are display only.

    void setupRender() throws Exception {
        // Convert the id into a Person.
        person = personFinderService.findPerson(personId);
        if (person == null) {
            throw new Exception("Database data has not been set up!");
        }
    }
}
