package com.example.newapp.encoders;

import org.apache.tapestry5.ValueEncoder;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.Person;

public class PersonEncoder implements ValueEncoder<Person> {


    private IPersonFinderServiceLocal personFinderService;
	
    public PersonEncoder(IPersonFinderServiceLocal personFinderService) {
        this.personFinderService = personFinderService;
    }
    
	@Override
	public String toClient(Person value) {
		 return String.valueOf(value.getId());
	}

	@Override
	public Person toValue(String id) {
		return personFinderService.findPerson(Long.parseLong(id));
	}

}
