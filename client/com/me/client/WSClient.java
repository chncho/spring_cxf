package com.me.client;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.me.cxf.services.IPersonService;

enum  WSClient implements Serializable
{
	INSTANCE;  
	private String url= "http://127.0.0.1:8080/spring_cxf/services/personService";
	
	private IPersonService iPersonService;
	
	public IPersonService getService()
    {
        JaxWsProxyFactoryBean f1 = new JaxWsProxyFactoryBean();
        f1.setAddress(url);
        f1.setServiceClass(IPersonService.class);
        logSOAP(f1);
        iPersonService = (IPersonService) f1.create();
        return iPersonService;
    }
	
    /**
     * 拦截输入输出参数
     */
    @SuppressWarnings("unused")
    private static void logSOAP(JaxWsProxyFactoryBean factory)
    {
		Log cxfInLogger = LogFactory
                .getLog("org.apache.cxf.interceptor.LoggingInInterceptor");
        Log cxfOutLogger = LogFactory
                .getLog("org.apache.cxf.interceptor.LoggingOutInterceptor");
    }
}
