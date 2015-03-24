package com.example.newapp.pages.examples.infrastructure;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;

public class HandlingABadContext {
	 // The activation context

    @Property
    private Long personId;

    // Screen fields

    @Property
    private Person person;

    // Generally useful bits and pieces

    //@EJB
    @Inject
    private IPersonFinderServiceLocal personFinderService;

    // The code

    Long onPassivate() {
        return personId;
    }

    void onActivate(Long personId) {
        this.personId = personId;
    }

    void setupRender() {
        // Get person - ask business service to find it (from the database)
        person = personFinderService.findPerson(personId);
        // Handle null person in the template (with an If component).
    }
}
