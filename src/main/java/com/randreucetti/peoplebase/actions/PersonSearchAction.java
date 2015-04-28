package com.randreucetti.peoplebase.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.randreucetti.peoplebase.dao.PersonDao;

public class PersonSearchAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6450342223484456570L;
	
	private PersonDao personDao;

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
