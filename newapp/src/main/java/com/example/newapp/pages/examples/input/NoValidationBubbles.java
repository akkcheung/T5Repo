package com.example.newapp.pages.examples.input;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import com.example.newapp.components.CustomForm;

//The @Import tells Tapestry to put a link to the file in the head of the page so that the browser will pull it in. 

@Import(library = "context:js/letters.js")
public class NoValidationBubbles {
	 // Screen fields

	/*
	@Component(id = "names")
	private CustomForm form;
	*/
	
    @Property
    @Persist(PersistenceConstants.FLASH)
    private String firstName;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String lastName;
    
    
    
   
	

}
