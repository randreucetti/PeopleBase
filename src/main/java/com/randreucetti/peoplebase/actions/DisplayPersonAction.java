package com.randreucetti.peoplebase.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.randreucetti.peoplebase.dao.PersonDao;

public class DisplayPersonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015340253659768781L;

	private PersonDao personDao;

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
