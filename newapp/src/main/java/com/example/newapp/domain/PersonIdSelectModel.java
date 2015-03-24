package com.example.newapp.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.SelectModelVisitor;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.util.AbstractSelectModel;

import com.example.newapp.entities.Person;

public class PersonIdSelectModel  extends AbstractSelectModel  {

	 	private List<Person> persons;

	    public PersonIdSelectModel(List<Person> persons) {
	        this.persons = persons;
	    }
	    
	    @Override
	    public List<OptionGroupModel> getOptionGroups() {
	        return null;
	    }

	    @Override
	    public List<OptionModel> getOptions() {
	        List<OptionModel> options = new ArrayList<OptionModel>();
	        for (Person person : persons) {
	            options.add(new OptionModelImpl(person.getFirstName(), person.getId()));
	        }
	        return options;
	    }

}
