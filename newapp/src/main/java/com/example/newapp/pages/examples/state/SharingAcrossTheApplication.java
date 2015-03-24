package com.example.newapp.pages.examples.state;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.newapp.services.CountryNames;



public class SharingAcrossTheApplication {

    // Screen fields

    @Inject
    @Property
    private CountryNames countryNames;
  
}
