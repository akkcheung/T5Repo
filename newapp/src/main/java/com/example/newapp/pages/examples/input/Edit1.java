package com.example.newapp.pages.examples.input;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.IPersonManagerServiceLocal;
import com.example.newapp.entities.Person;
import com.example.newapp.util.ExceptionUtil;

public class Edit1 {
	 // The activation context

    private Long personId;

    // Screen fields

    @Property
    private Person person;

    // Work fields

    // This carries version through the redirect that follows a server-side validation failure.
    @Persist(PersistenceConstants.FLASH)
    private Integer versionFlash;

    // Other pages

    @InjectPage
    private Edit2 page2;

    // Generally useful bits and pieces

    @Component(id = "personForm")
    private BeanEditForm form;

    // @EJB
    @Inject
    private IPersonFinderServiceLocal personFinderService;

    // @EJB
    @Inject
    private IPersonManagerServiceLocal personManagerService;

    // The code

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.

    Long onPassivate() {
        return personId;
    }

    // onActivate() is called by Tapestry to pass in the activation context from the URL.

    void onActivate(Long personId) {
        this.personId = personId;
    }

    // Form bubbles up the PREPARE_FOR_RENDER event during form render.

    void onPrepareForRender() throws Exception {
        person = findPerson(personId);

        // If the form has errors then we're redisplaying after a redirect.
        // Form will restore your input values but it's up to us to restore Hidden values.

        if (form.getHasErrors()) {
            person.setVersion(versionFlash);
        }
    }

    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

    void onPrepareForSubmit() throws Exception {
        // Get objects for the form fields to overlay.
        person = findPerson(personId);
    }

    void onValidateFromPersonForm() {

        if (form.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        /*
        try {
            personManagerService.changePerson(person);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            form.recordError(ExceptionUtil.getRootCauseMessage(e));
        }
        */
        
        System.out.println(person.getFirstName());
        
        personManagerService.changePerson(person);
    }

    Object onSuccess() {
        page2.set(personId);
        return page2;
    }
    
    void onFailure() {
        versionFlash = person.getVersion();
    }

    void onRefresh() {
        // By doing nothing the page will be displayed afresh.
    }

    private Person findPerson(Long personId) throws Exception {
        Person person = personFinderService.findPerson(personId);

        if (person == null) {
            if (personId < 4) {
                throw new IllegalStateException("Database data has not been set up!");
            }
            else {
                throw new Exception("Person " + personId + " does not exist.");
            }
        }

        return person;
    }
}
