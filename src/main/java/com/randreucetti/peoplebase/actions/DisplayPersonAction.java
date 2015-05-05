package com.randreucetti.peoplebase.actions;

import java.util.Date;

import com.randreucetti.peoplebase.dao.PersonDao;
import com.randreucetti.peoplebase.model.Person;

public class DisplayPersonAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015340253659768781L;

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

	public void open() {
		if (id != null) {
			logger.info("Opening person of id: {}", id);
			Person p = personDao.getById(id);
			populateFields(p);
		} else {
			logger.info("creating new person");
		}
	}

	public void save() {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setSurname(surname);
		person.setPhone(phone);
		person.setEmail(email);
		person.setGender(gender);
		person.setDateOfBirth(dateOfBirth);
		person.setCreationTime(creationTime == null ? new Date() : creationTime);
		logger.info("Attempting to save person {}", person);
		personDao.save(person);
	}

	@Override
	public void validate() {
		logger.info("Validate being called");
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

}
