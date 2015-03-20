package com.example.newapp.pages.examples.state;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import com.examples.newapp.services.CountryNames;



public class SharingAcrossTheApplication {

    // Screen fields

    @Inject
    @Property
    private CountryNames countryNames;
  
}
