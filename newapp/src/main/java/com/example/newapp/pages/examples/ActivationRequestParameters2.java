package com.example.newapp.pages.examples;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;

public class ActivationRequestParameters2 {


    // Activation request parameters

    @Property
    @ActivationRequestParameter(value = "personid")
    private Long personId;

    // Screen fields

    @Property
    private Person person;

    // Generally useful bits and pieces

    //@EJB
    @Inject
    private IPersonFinderServiceLocal personFinderService;

    // The code

    // setupRender() is called by tapestry at the start of rendering - it's good for things that are display only.

    void setupRender() throws Exception {
        // Get person - ask business service to find it (from the database)
        person = personFinderService.findPerson(personId);

        if (person == null) {
            throw new Exception("Database data has not been set up!");
        }
    }
	
}
