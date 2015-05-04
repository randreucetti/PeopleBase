package com.randreucetti.peoplebase.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.randreucetti.peoplebase.dao.PersonDao;
import com.randreucetti.peoplebase.util.PersonUtil;
import com.randreucetti.peoplebase.vo.PersonVO;

public class DisplayPersonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015340253659768781L;

	private PersonDao personDao;
	
	private Long id;
	private PersonVO vo;

	public void open(){
		if(id != null){
			setVo(PersonUtil.convertModeltoVO(personDao.getById(id)));
		} else {
			setVo(new PersonVO());
		}
	}
	
	public void save(){
		
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

	public PersonVO getVo() {
		return vo;
	}

	public void setVo(PersonVO vo) {
		this.vo = vo;
	}
}
