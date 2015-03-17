package com.example.newapp.pages.examples;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet="css/olive.css")
public class BetterOutput {
	
	@Property
    private String message;

    void setupRender() {
        message = "server time: " + new java.util.Date() + ".";
    }
}
