package com.example.newapp.pages.examples;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.data.Regions;
import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;

public class TotalControlOutput {

	  	@Property
	    private Person person;

	    // @EJB
	    @Inject
	    private IPersonFinderServiceLocal personFinderService;

	    @Inject
	    private Messages messages;

	    void setupRender() {
	        Long personId = 1L;
	        // Get person - ask business service to find it (from the database)
	        person = personFinderService.findPerson(personId);

	        if (person == null) {
	            throw new IllegalStateException("Database data has not been set up!");
	        }
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
