package com.example.newapp.pages.examples.tables;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;

public class GridBeanModel1 {
	  static private final int MAX_RESULTS = 30;

	    // Screen fields

	    @Property
	    private List<Person> persons;

	    @Property
	    private Person person;

	    @Property
	    private BeanModel<Person> myModel;

	    // Generally useful bits and pieces

	    //@EJB
	    @Inject
	    private IPersonFinderServiceLocal personFinderService;

	    @Inject
	    private BeanModelSource beanModelSource;

	    @Inject
	    private Messages messages;

	    // The code

	    void setupRender() {

	        myModel = beanModelSource.createDisplayModel(Person.class, messages);
	        myModel.add("action", null);
	        myModel.include("id", "firstName", "lastName", "startDate", "action");
	        myModel.get("firstName").sortable(false);
	        myModel.get("lastName").label("Surname");

	        // Get all persons - ask business service to find them (from the database)
	        persons = personFinderService.findPersons(MAX_RESULTS);
	    }
}
