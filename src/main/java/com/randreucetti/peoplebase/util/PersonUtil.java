package com.randreucetti.peoplebase.util;

import com.randreucetti.peoplebase.model.Person;
import com.randreucetti.peoplebase.vo.PersonVO;

public class PersonUtil {
	public static Person convertVOtoModel(PersonVO vo){
		Person p = new Person();
		p.setFirstName(vo.getFirstName());
		p.setSurname(vo.getSurname());
		p.setPhone(vo.getPhone());
		p.setEmail(vo.getEmail());
		p.setCountry(vo.getCountry());
		p.setGender(vo.getGender());
		p.setDateOfBirth(vo.getDateOfBirth());
		p.setCreationTime(vo.getCreationTime());
		return p;
	}
	
	public static PersonVO convertModeltoVO(Person model){
		PersonVO p = new PersonVO();
		p.setFirstName(model.getFirstName());
		p.setSurname(model.getSurname());
		p.setPhone(model.getPhone());
		p.setEmail(model.getEmail());
		p.setCountry(model.getCountry());
		p.setGender(model.getGender());
		p.setDateOfBirth(model.getDateOfBirth());
		p.setCreationTime(model.getCreationTime());
		return p;
	}
}
