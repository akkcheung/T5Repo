package com.example.newapp.pages.examples.state;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import com.example.newapp.data.ShoppingBasket;
import com.example.newapp.pages.Index;

public class SharingAcrossMultiplePages2 {

	// Screen fields

		@SessionState
		@Property
		private ShoppingBasket myBasket;
		
		// The code

		Object onGoHome() {
			// Delete the SSO from the session
			myBasket = null;
			return Index.class;
		}
	
}
