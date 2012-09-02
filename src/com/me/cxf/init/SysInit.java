package com.me.cxf.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoaderListener;

public class SysInit extends ContextLoaderListener implements
		ServletContextListener
{

	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
	}

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		String syspath = event.getServletContext().getContextPath();
		// WebPath.SYS_PATH=syspath;
		System.out.println("项目名称："+syspath);
	}

}
