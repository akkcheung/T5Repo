package com.example.newapp.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import com.example.newapp.data.Honorific;



@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	// public Long id;
	@Property
	private Long id;
	
	// public Honorific honorific;
	@Property
	private Honorific honorific;
	
	@Validate("required")
    // public String firstName;
	@Property
	private String firstName;
	
	
	@Validate("required")
    // public String lastName;
    // public String street1;
    // public String street2;
	@Property
	private String lastName;
	
	@Property
	private String street1;
	
	@Property
	private String street2;
    
    @Validate("required")
    // public String city;
    @Property
    private String city;
    
    @Validate("required")
    // public String state;
    @Property
    private String state;
    
    @Validate("required,regexp=^\\d{5}(-\\d{4})?$")
    // public String zip;
    @Property
    private String zip;
    
    // public String email;
    // public String phone;
    
    @Property
    private String email;
    
    @Property
    private String phone;

    
    
}

