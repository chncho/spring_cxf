package com.me.cxf.services;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.me.vo.VOPerson;



@SuppressWarnings("restriction")
@WebService
public interface  IPersonService
{
	@WebResult(name = "userList")
    List<VOPerson> listPerson(@WebParam(name = "username")String userName_ );
    
    
}
