package com.randreucetti.peoplebase.dao;

import java.util.List;

import com.randreucetti.peoplebase.model.Person;

public interface PersonDao {
	public void save(Person p);

	public Person getById(int id);

	public void delete(Person p);

	public Long count();

	public List<Person> retreiveAll();
}
