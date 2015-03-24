package com.example.newapp.services;


import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

import com.example.newapp.util.StringUtil;

public class CountryNames {
	
	private Set<String> countryNames = new TreeSet<String>();

	@Inject
    private Logger logger;

	
    public CountryNames() {
        Locale[] availableLocales = Locale.getAvailableLocales();

        for (Locale locale : availableLocales) {
            if (StringUtil.isNotEmpty(locale.getDisplayCountry())) {

            	countryNames.add(locale.getDisplayCountry().toUpperCase());                
                
            	logger.info(locale.getDisplayCountry());  
                
            }
        }
    }

    public Set<String> getSet() {
        return Collections.unmodifiableSet(countryNames);
    }
}
