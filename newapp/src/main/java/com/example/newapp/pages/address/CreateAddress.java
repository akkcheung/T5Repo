package com.example.newapp.pages.address;

import javax.persistence.EntityManager;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
// import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
// import org.hibernate.Session;
import org.apache.tapestry5.jpa.annotations.CommitAfter;



import com.example.newapp.entities.Address;
import com.example.newapp.pages.Index;

public class CreateAddress {

	@Property
	private Address address;

	
	// @Inject
	// private Session session;
	
	@Inject
	private EntityManager entityManager;
	

	@InjectPage
	private Index index;

	
	@CommitAfter
	Object onSuccess() {
		// session.persist(address);
		 entityManager.persist(address);

		return index;
	}
	

}
