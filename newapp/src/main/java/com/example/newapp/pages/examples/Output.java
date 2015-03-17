package com.example.newapp.pages.examples;

import org.apache.tapestry5.annotations.Import;


@Import(stylesheet="css/olive.css")
public class Output {
	
	public String getMessage() {
        return "I am a message from the server, where the time is " + new java.util.Date() + ".";
    }
}
