package com.example.newapp.pages.examples.input;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.entities.IPersonManagerServiceLocal;
import com.example.newapp.entities.Person;
import com.example.newapp.util.ExceptionUtil;

public class Create1 {
	
	//private final String demoModeStr = System.getProperty("jumpstart.demo-mode");
	private final String demoModeStr = "false";

    // Screen fields

    @Property
    private Person person;

    // Other pages

    @InjectPage
    private Create2 page2;

    // Generally useful bits and pieces

    @Component(id = "personForm")
    private BeanEditForm personForm;

    // @EJB
    @Inject
    private IPersonManagerServiceLocal personManagerService;

    // The code

    // Form bubbles up the PREPARE event during form render and form submission.

    void onPrepare() throws Exception {
        person = new Person();
    }

    void onValidateFromPersonForm() {
        if (demoModeStr != null && demoModeStr.equals("true")) {
            personForm.recordError("Sorry, but this function is not allowed in Demo mode.");
            return;
        }
        
        /*
        try {
            personManagerService.createPerson(person);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            personForm.recordError(ExceptionUtil.getRootCauseMessage(e));
        }
        */
        
        personManagerService.createPerson(person);
    }

    Object onSuccess() {
        page2.set(person.getId());
        return page2;
    }

    void onRefresh() {
        // By doing nothing the page will be displayed afresh.
    }
}
