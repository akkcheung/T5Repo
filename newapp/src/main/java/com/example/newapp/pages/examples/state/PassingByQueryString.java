package com.example.newapp.pages.examples.state;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

public class PassingByQueryString {

	// Query string parameters

    @ActivationRequestParameter(value = "first")
    private String firstName;

    @ActivationRequestParameter(value = "last")
    private String lastName;

    // Generally useful bits and pieces

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    // The code

    // set() is public so that other pages can use it to get a correct link to this page.

    public Link set(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        return pageRenderLinkSource.createPageRenderLink(this.getClass());
    }

    public String getName() {
        return firstName + " " + lastName;
    }

}
