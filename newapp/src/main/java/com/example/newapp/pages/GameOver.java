package com.example.newapp.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class GameOver {

	@Property
    @Persist
    private int target, guessCount;
	
	public void setup(int target, int guessCount) {
		// TODO Auto-generated method stub
	
		this.target = target;
        this.guessCount = guessCount;
	}

}
