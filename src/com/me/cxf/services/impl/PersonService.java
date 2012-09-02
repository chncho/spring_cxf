package com.me.cxf.services.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.biz.PersonBiz;
import com.me.cxf.services.IPersonService;
import com.me.vo.VOPerson;

public class PersonService implements IPersonService
{
	@Autowired
	private  PersonBiz personBiz;
	
	public List<VOPerson>  listPerson(String userName)
	{
		List<VOPerson> out=new ArrayList<VOPerson>();
		try
		{
			out=personBiz.listPersons();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return out;
	}
		
}
