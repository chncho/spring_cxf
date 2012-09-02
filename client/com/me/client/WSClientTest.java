package com.me.client;

import java.util.List;

import org.junit.Test;

import com.me.vo.VOPerson;

public class WSClientTest
{

	@Test
	public void test01()
	{

		List<VOPerson> list = WSClient.INSTANCE.getService().listPerson("小明");
		
		System.out.println(list.size());

	}

}
