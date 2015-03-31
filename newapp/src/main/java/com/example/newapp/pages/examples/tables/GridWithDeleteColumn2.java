package com.example.newapp.pages.examples.tables;

import java.util.Collection;
import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import com.example.newapp.commons.IdVersion;

public class GridWithDeleteColumn2 {

	 // Screen fields

    @Property
    @Persist(PersistenceConstants.FLASH)
    private Collection<IdVersion> persons;

    @Property
    private IdVersion person;

    // The code

    public void set(Collection<IdVersion> personsDeleted) {
        this.persons = personsDeleted;
    }

}
