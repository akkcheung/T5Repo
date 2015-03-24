package com.example.newapp.pages.examples.select;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;



import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;
import com.example.newapp.web.services.SelectIdModelFactory;

public class EasyIdSelect {
	 static private final int MAX_RESULTS = 30;

	    // The activation context

	    @Property
	    private Long personId;

	    // Screen fields

	    @Property
	    private SelectModel personIdsModel;

	    // Generally useful bits and pieces

	    @Inject
	    private SelectIdModelFactory selectIdModelFactory;

	    //@EJB
	    @Inject
	    private IPersonFinderServiceLocal personFinderService;

	    // The code

	    Long onPassivate() {    
	        return personId;
	    }

	    void onActivate(EventContext context) {
	        if (context.getCount() > 0) {
	            personId = context.get(Long.class, 0);
	        }
	    }

	    void onPrepareForRender() {
	        // Get all persons - ask business service to find them (from the database)
	        List<Person> persons = personFinderService.findPersons(MAX_RESULTS);

	        personIdsModel = selectIdModelFactory.create(persons, "firstName", "id");
	    }
}
