package com.usermanager.helper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.usermanager.model.User;

public class Helper {

	public List<User> sortByFamily(List<User> unsortedList){
		
		Collections.sort(unsortedList, new Comparator<User>(){

			@Override
			public int compare(User arg0, User arg1) {
				return arg0.getLastName().compareTo(arg1.getLastName());
			}
			
		});
		return unsortedList;
	}
			
	public List<User> sortByDob(List<User> unsortedList){
		
		Collections.sort(unsortedList, new Comparator<User>(){

			@Override
			public int compare(User arg0, User arg1) {
				return arg0.getDateOfBirth().compareTo(arg1.getDateOfBirth());
			}
			
		});
		return unsortedList;
	}
	
}
