package com.me.biz;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.me.vo.VOPerson;

public class PersonMapper implements RowMapper<VOPerson>
{
	public VOPerson mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		VOPerson voPerson = new VOPerson();
		voPerson.setAge(rs.getInt("age"));
		voPerson.setID(rs.getString("ID"));
		voPerson.setUserName(rs.getString("userName"));
//		System.out.println("voPeronsMapper:" + rowNum);
		return voPerson;
	}
}
