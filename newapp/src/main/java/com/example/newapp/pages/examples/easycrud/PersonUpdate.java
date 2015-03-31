package com.example.newapp.pages.examples.easycrud;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.components.CustomForm;
import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.IPersonManagerServiceLocal;
import com.example.newapp.entities.Person;
import com.example.newapp.util.ExceptionUtil;

public class PersonUpdate {
	 // The activation context

    @Property
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
    private Persons indexPage;

    // Generally useful bits and pieces

    @Component(id = "personForm")
    // private CustomForm personForm;
    private Form personForm;

    //@EJB
    @Inject
    private IPersonFinderServiceLocal personFinderService;

    //@EJB
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

    // PersonForm bubbles up the PREPARE_FOR_RENDER event during form render.

    void onPrepareForRender() {
        person = personFinderService.findPerson(personId);
        // Handle null person in the template.

        // If the form has errors then we're redisplaying after a redirect.
        // Form will restore your input values but it's up to us to restore Hidden values.

        if (personForm.getHasErrors()) {
            if (person != null) {
                person.setVersion(versionFlash);
            }
        }
    }

    // PersonForm bubbles up the PREPARE_FOR_SUBMIT event during form submission.

    void onPrepareForSubmit() {
        // Get objects for the form fields to overlay.
        person = personFinderService.findPerson(personId);

        if (person == null) {
            person = new Person();
            personForm.recordError("Person has been deleted by another process.");
        }
    }

    // PersonForm bubbles up the VALIDATE event when it is submitted

    void onValidateFromPersonForm() {

        if (personForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
            personManagerService.changePerson(person);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            personForm.recordError(ExceptionUtil.getRootCauseMessage(e));
        }
    }

    // PersonForm bubbles up SUCCESS or FAILURE when it is submitted, depending on whether VALIDATE records an error

    Object onSuccess() {
        return indexPage;
    }

    void onFailure() {
        versionFlash = person.getVersion();
    }
}
