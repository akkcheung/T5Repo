package com.example.newapp.pages.examples.tables;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.corelib.components.Grid;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.model.tables.PersonPagedDataSource;

public class GridDataSources {
	 // Screen fields

    @Property
    private GridDataSource persons;

    // Generally useful bits and pieces

    //@EJB
    @Inject
    private IPersonFinderServiceLocal personFinderService;

    @InjectComponent
    private Grid grid;

    // The code

    void setupRender() {
        persons = new PersonPagedDataSource(personFinderService);

        if (grid.getSortModel().getSortConstraints().isEmpty()) {
            grid.getSortModel().updateSort("firstName");
        }
    }
}
