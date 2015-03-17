package com.example.newapp.pages.examples;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet = "css/plain.css")
public class EasyOutput {

	@Property
    private String name;

    @Property
    private Integer age;

    @Property
    private Gender gender;
    
    void setupRender() {

        name = "Jane Citizen";
        age = 25;
        gender = Gender.FEMALE;

    }
    
    private enum Gender {
        MALE, FEMALE;
    }

	
}
