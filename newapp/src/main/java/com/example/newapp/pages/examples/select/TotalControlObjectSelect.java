package com.example.newapp.pages.examples.select;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.domain.PersonSelectModel;
import com.example.newapp.encoders.PersonEncoder;
import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;

public class TotalControlObjectSelect {
	 	static private final int MAX_RESULTS = 30;

	    // The activation context

	    private Long personId;

	    // Screen fields

	    @Property
	    private SelectModel personsModel;

	    @Property
	    private Person person;

	    // Generally useful bits and pieces

	    //@EJB
	    @Inject
	    private IPersonFinderServiceLocal personFinderService;

	    // The code
	    
	    Long onPassivate() {
	        return person == null ? null : person.getId();
	    }

	    void onActivate(EventContext context) {
	        if (context.getCount() > 0) {
	            personId = context.get(Long.class, 0);
	        }
	    }

	    void onPrepareForRender() {
	        // Get all persons - ask business service to find them (from the database)
	        List<Person> persons = personFinderService.findPersons(MAX_RESULTS);
	        
	        if (personId != null) {
	            person = findPersonInList(personId, persons);
	        }

	        personsModel = new PersonSelectModel(persons);
	    }

	    void onValidateFromForm() {
	        personId = person == null ? null : person.getId();
	    }

	    private Person findPersonInList(Long personId, List<Person> persons) {
	        for (Person person : persons) {
	            if (person.getId().equals(personId)) {
	                return person;
	            }
	        }
	        return null;
	    }

	    public PersonEncoder getPersonEncoder() {
	        return new PersonEncoder(personFinderService);
	    }
}
