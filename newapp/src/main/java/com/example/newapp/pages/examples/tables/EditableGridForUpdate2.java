package com.example.newapp.pages.examples.tables;

import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import com.example.newapp.entities.Person;

public class EditableGridForUpdate2 {
	
	// Screen fields

    @Property
    @Persist(PersistenceConstants.FLASH)
    private List<Person> persons;

    // The code

    public void set(List<Person> persons) {
        this.persons = persons;
    }
}
