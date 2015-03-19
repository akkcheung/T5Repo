package com.example.newapp.pages.examples.component;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.corelib.components.PageLink;

public class AtComponent {

    // This provides parameter bindings for the "home" component.
    
    @Component(id = "home", parameters = {"page=Index"})
    private PageLink index;
    
}
