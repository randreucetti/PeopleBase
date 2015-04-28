package com.randreucetti.peoplebase.dao.impl;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.PropertyValueException;
import org.junit.Test;

import com.randreucetti.peoplebase.dao.PersonDao;
import com.randreucetti.peoplebase.model.Person;

public class PersonDaoImplTest {

	@Test
	public void testCrud() throws ParseException {
		PersonDao personDao = new PersonDaoImpl();
		Long rowCount = personDao.count();
		Person p = new Person();
		p.setFirstName("Ross");
		p.setSurname("Andreucetti");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		p.setDateOfBirth(sdf.parse("23/08/1990"));
		p.setCreationTime(new Date());
		p.setGender('M');
		p.setEmail("ross.andreucetti@gmail.com");
		p.setCountry("Singapore");
		personDao.save(p);
		assertTrue(rowCount < personDao.count());
		p.setCountry("Ireland");
		personDao.save(p);
		personDao.delete(p);
		assertTrue(rowCount == personDao.count());
	}

	@Test(expected=PropertyValueException.class)
	public void testNullField() {
		PersonDao personDao = new PersonDaoImpl();
		Person p = new Person();
		p.setFirstName("Ross");
		p.setSurname("Andreucetti");
		p.setCreationTime(new Date());
		p.setGender('M');
		p.setEmail("ross.andreucetti@gmail.com");
		p.setCountry("Singapore");
		personDao.save(p);
	}
}
