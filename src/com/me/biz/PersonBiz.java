package com.me.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.me.vo.VOPerson;

@Component
public class PersonBiz
{

	@Autowired
	private JdbcTemplate dao;
	
	public List<VOPerson> listPersons()
	{
		
//		BeanPropertyRowMapper<VOPerson> mapper=BeanPropertyRowMapper.newInstance(VOPerson.class);
//		dao.queryForObject("select * from t_person", mapper);
		List<VOPerson> list=dao.query("select * from t_person", new PersonMapper());
		
		int i=0;
		while(i<6)
		{
			i++;
			System.err.println("服务器端正在处理任务");
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}
}
