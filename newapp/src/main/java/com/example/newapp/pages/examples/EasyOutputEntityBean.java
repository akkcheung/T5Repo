package com.example.newapp.pages.examples;

// import javax.ejb.EJB;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;

@Import(stylesheet = "css/plain.css")
public class EasyOutputEntityBean {

	@Property
	private Person person;

	//@EJB
	@Inject
	private IPersonFinderServiceLocal personFinderService;
		

	void setupRender() {

		// Get person with id 1 - ask business service to find it (from the
		// database).

		person = personFinderService.findPerson(1L);

		if (person == null) {
			throw new IllegalStateException(
					"Database data has not been set up!");
		}
	}

}
