package com.randreucetti.peoplebase.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.randreucetti.peoplebase.dao.PersonDao;
import com.randreucetti.peoplebase.model.Person;
import com.randreucetti.peoplebase.util.HibernateUtil;

public class PersonDaoImpl implements PersonDao {

	@Override
	public void save(Person p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(p);
		session.getTransaction().commit();
	}

	@Override
	public Person getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Person) session.get(Person.class, id);
	}

	@Override
	public void delete(Person p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
	}

	@Override
	public Long count() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Person.class);
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.list().get(0)).longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> retreiveAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session.createCriteria(Person.class).list();
	}

}
