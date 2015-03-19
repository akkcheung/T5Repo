package com.example.newapp.util;

import com.example.newapp.data.SortDirection;

public class SortCriterion {

	private String propertyName;
	private SortDirection sortDirection;
	
	public SortCriterion(String propertyName, SortDirection sortDirection) {
		this.propertyName = propertyName;
		this.sortDirection = sortDirection;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public SortDirection getSortDirection() {
		return sortDirection;
	}
}
