package com.example.newapp.pages.examples.easycrud;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.data.Regions;
import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;

public class PersonReview {
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

    @Inject
    private Messages messages;

    // The code

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.

    Long onPassivate() {
        return personId;
    }

    // onActivate() is called by Tapestry to pass in the activation context from the URL.

    void onActivate(Long personId) {
        this.personId = personId;
    }

    // setupRender() is called by Tapestry right before it starts rendering the page.

    void setupRender() {
        person = personFinderService.findPerson(personId);
        // Handle null person in the template.
    }

    public String getPersonRegion() {
        // Follow the same naming convention that the Select component uses
        return messages.get(Regions.class.getSimpleName() + "." + person.getRegion().name());
    }

    public Format getStartDateFormat() {
        final Format f = new SimpleDateFormat("dd MMMM yyyy G");
        return f;
    }
}
