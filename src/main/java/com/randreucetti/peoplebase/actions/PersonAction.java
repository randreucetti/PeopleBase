package com.randreucetti.peoplebase.actions;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.randreucetti.peoplebase.dao.PersonDao;
import com.randreucetti.peoplebase.model.Person;

public class PersonAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6450342223484456570L;

	private PersonDao personDao;

	private Long id;
	private String firstName;
	private String surname;
	private String phone;
	private String email;
	private String country;
	private Character gender;
	private Date dateOfBirth;
	private Date creationTime;

	public void search() throws Exception {
		List<Person> people = personDao.retreiveAll();
		logger.info("search returned {} rows", people.size());
		JSONObject peopleDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (Person p : people) {
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

	public void open() {
		if (id != null) {
			logger.info("Opening person of id: {}", id);
			Person p = personDao.getById(id);
			populateFields(p);
		} else {
			logger.info("creating new person");
		}
	}

	public void save() throws ServletException, IOException {
		logger.info("starting save");
		JSONObject responseJson = new JSONObject();
		JSONArray errorJsonArray = new JSONArray();
		if (firstName == null || firstName.equals("")) {
			JSONObject errorMsgJson = new JSONObject();
			errorMsgJson.put("msg", "First name must be set");
			errorJsonArray.put(errorMsgJson);
		}
		if (surname == null || surname.equals("")) {
			JSONObject errorMsgJson = new JSONObject();
			errorMsgJson.put("msg", "Surname must be set");
			errorJsonArray.put(errorMsgJson);
		}
		if (dateOfBirth == null || dateOfBirth.equals("")) {
			JSONObject errorMsgJson = new JSONObject();
			errorMsgJson.put("msg", "dateOfBirth must be set");
			errorJsonArray.put(errorMsgJson);
		}
		responseJson.put("errorList", errorJsonArray);
		logger.info("json: {}", responseJson.toString());
		if (errorJsonArray.length() == 0) {
			Person person = new Person();
			person.setFirstName(firstName);
			person.setSurname(surname);
			person.setPhone(phone);
			person.setEmail(email);
			person.setGender('M');
			person.setDateOfBirth(dateOfBirth);
			person.setCreationTime(creationTime == null ? new Date() : creationTime);
			logger.info("Attempting to save person {}", person);
			try {
				personDao.save(person);
				id = person.getId();
				JSONObject personJson = new JSONObject();
				personJson.put("firstName", firstName);
				personJson.put("surname", surname);
				personJson.put("creationTime", creationTime == null ? new Date() : creationTime);
				personJson.put("id", id);
				responseJson.put("person", personJson);
			} catch (HibernateException e) {
				logger.error("hibernate error {}", e);
				throw new ServletException();
			}
		}
		
		getWriter().print(responseJson.toString());

	}

	private void populateFields(Person p) {
		firstName = p.getFirstName();
		surname = p.getSurname();
		phone = p.getPhone();
		email = p.getEmail();
		country = p.getCountry();
		gender = p.getGender();
		dateOfBirth = p.getDateOfBirth();
		creationTime = p.getCreationTime();
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

}
