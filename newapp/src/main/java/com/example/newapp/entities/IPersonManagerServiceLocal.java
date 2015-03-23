package com.example.newapp.entities;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.apache.tapestry5.jpa.annotations.CommitAfter;

import com.example.newapp.commons.IdVersion;

public interface IPersonManagerServiceLocal {
		// Person
		
		@CommitAfter
		@PersistenceContext(unitName = "jumpstart")
		Person createPerson(Person person);		
		
		void createPersons(List<Person> persons);
		
		@CommitAfter
		@PersistenceContext(unitName = "jumpstart")
		void changePerson(Person person);

		void changePersons(List<Person> persons);

		void changePersonsByDTOs(List<PersonDTO> persons);

		void bulkEditPersons(List<Person> personsToCreate, List<Person> personsToChange, List<IdVersion> personsToDelete);

		void bulkEditPersonsByDTOs(List<PersonDTO> personsToCreate, List<PersonDTO> personsToChange,
				List<IdVersion> personsToDelete);

		void deletePerson(Long id, Integer version);
}
