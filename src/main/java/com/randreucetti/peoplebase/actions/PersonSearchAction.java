package com.randreucetti.peoplebase.actions;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.randreucetti.peoplebase.dao.PersonDao;
import com.randreucetti.peoplebase.model.Person;

public class PersonSearchAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6450342223484456570L;

	private PersonDao personDao;

	public void search() throws Exception {
		List <Person> people = personDao.retreiveAll();
		logger.info("search returned {} rows", people.size());
		JSONObject peopleDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(Person p: people){
			JSONObject personDetailsJson = new JSONObject();
			personDetailsJson.put("id", p.getId());
			personDetailsJson.put("firstName", p.getFirstName());
			personDetailsJson.put("surname", p.getSurname());
			personDetailsJson.put("creationTime", p.getCreationTime());
			jsonArray.put(personDetailsJson);
		}
		peopleDetailsJson.put("list", jsonArray);
		logger.info("json: {}", peopleDetailsJson.toString());
		getWriter().print(peopleDetailsJson.toString());
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}


}
