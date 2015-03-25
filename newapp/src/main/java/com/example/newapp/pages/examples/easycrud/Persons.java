package com.example.newapp.pages.examples.easycrud;

import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.IPersonManagerServiceLocal;
import com.example.newapp.entities.Person;
import com.example.newapp.util.ExceptionUtil;

public class Persons {
	 	// private final String demoModeStr = System.getProperty("jumpstart.demo-mode");
		private final String demoModeStr = "true";
		
	    static private final int MAX_RESULTS = 30;

	    // Screen fields

	    @Property
	    private List<Person> persons;

	    @Property
	    private Person person;

	    @Property
	    @Persist(PersistenceConstants.FLASH)
	    private String errorMessage;

	    // Generally useful bits and pieces

	    //@EJB
	    @Inject
	    private IPersonFinderServiceLocal personFinderService;

	    //@EJB
	    @Inject
	    private IPersonManagerServiceLocal personManagerService;

	    // The code

	    // setupRender() is called by Tapestry right before it starts rendering the page.

	    void setupRender() {
	        persons = personFinderService.findPersons(MAX_RESULTS);
	    }

	    // Handle event "delete"

	    void onDelete(Long id, Integer version) {
	        if (demoModeStr != null && demoModeStr.equals("true")) {
	            errorMessage = "Sorry, but this function is not allowed in Demo mode.";
	            return;
	        }

	        try {
	            personManagerService.deletePerson(id, version);
	        }
	        catch (Exception e) {
	            // Display the cause. In a real system we would try harder to get a user-friendly message.
	            errorMessage = ExceptionUtil.getRootCauseMessage(e);
	        }
	    }
}
