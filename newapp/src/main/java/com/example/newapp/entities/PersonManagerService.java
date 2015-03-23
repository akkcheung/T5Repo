package com.example.newapp.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;

import com.example.newapp.commons.IdVersion;


public class PersonManagerService implements IPersonManagerServiceLocal {
	
	
	@Inject
	public EntityManager em;


	@CommitAfter	
	public Person createPerson(Person person) {
		em.persist(person);
		return person;
	}


	
	public void createPersons(List<Person> persons) {
		for (Person person : persons) {
			em.persist(person);
		}
	}


	

	public void changePerson(Person person) {
		
		System.out.println("PersonManagerService");
		System.out.println(person.getId());
		
		Person p = em.merge(person);
		// Flush to work around OPENEJB issue https://issues.apache.org/jira/browse/OPENEJB-782
		em.flush();

		// If id is different it means the person did not exist so merge has created a new one.
		if (!p.getId().equals(person.getId())) {
			throw new EntityNotFoundException("Person no longer exists.");
		}
	}


	public void changePersons(List<Person> persons) {
		for (Person person : persons) {
			Person p = em.merge(person);

			// If id is different it means the person did not exist so merge has created a new one.
			if (!p.getId().equals(person.getId())) {
				throw new EntityNotFoundException("Person no longer exists.");
			}
		}
	}


	public void changePersonsByDTOs(List<PersonDTO> persons) {
		for (PersonDTO person : persons) {
			Person p = em.find(Person.class, person.getId());

			if (p == null) {
				throw new EntityNotFoundException("Person no longer exists.");
			}

			if (!p.getVersion().equals(person.getVersion())) {
				throw new OptimisticLockException();
			}

			p.setFirstName(person.getFirstName());
		}
	}


	public void bulkEditPersons(List<Person> personsToCreate, List<Person> personsToChange,
			List<IdVersion> personsToDelete) {
		for (Person person : personsToCreate) {
			em.persist(person);
		}
		for (Person person : personsToChange) {
			Person p = em.merge(person);

			// If id is different it means the person did not exist so merge has created a new one.
			if (!p.getId().equals(person.getId())) {
				throw new EntityNotFoundException("Person no longer exists.");
			}
		}
		for (IdVersion idVersion : personsToDelete) {
			Person p = em.find(Person.class, idVersion.getId());

			if (p == null) {
				throw new EntityNotFoundException("Person no longer exists.");
			}

			if (!p.getVersion().equals(idVersion.getVersion())) {
				throw new OptimisticLockException();
			}

			em.remove(p);
		}
	}


	public void bulkEditPersonsByDTOs(List<PersonDTO> personsToCreate, List<PersonDTO> personsToChange,
			List<IdVersion> personsToDelete) {
		for (PersonDTO p : personsToCreate) {
			Person person = new Person(p.getFirstName(), p.getLastName(), p.getRegion(), p.getStartDate());
			em.persist(person);
		}
		for (PersonDTO person : personsToChange) {
			Person p = em.find(Person.class, person.getId());

			if (p == null) {
				throw new EntityNotFoundException("Person no longer exists.");
			}

			if (!p.getVersion().equals(person.getVersion())) {
				throw new OptimisticLockException();
			}

			p.setFirstName(person.getFirstName());
			p.setLastName(person.getLastName());
			p.setRegion(person.getRegion());
			p.setStartDate(person.getStartDate());
		}
		for (IdVersion idVersion : personsToDelete) {
			Person p = em.find(Person.class, idVersion.getId());

			if (p == null) {
				throw new EntityNotFoundException("Person no longer exists.");
			}

			if (!p.getVersion().equals(idVersion.getVersion())) {
				throw new OptimisticLockException();
			}

			em.remove(p);
		}
	}


	public void deletePerson(Long id, Integer version) {
		Person p = em.find(Person.class, id);

		if (p == null) {
			throw new EntityNotFoundException("Person no longer exists.");
		}

		if (!p.getVersion().equals(version)) {
			throw new OptimisticLockException();
		}

		em.remove(p);
		// Flush to work around OPENEJB issue https://issues.apache.org/jira/browse/OPENEJB-782
		em.flush();
	}

}
