package com.me.ut;

import java.io.File;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class WebPath
{
	 public static String SYS_PATH;
	 

	 

	    // 取得tomcat所在的盘符 eg: "c:\\"
	    public static String getTomcatPath()
	    {
	        return new File("/").getAbsolutePath();
	    }


	    /**
	     * 取得项目的classes文件夹 eg："C:/tomcat6/webapps/v/WEB-INF/classes/"
	     * 
	     * @return
	     */
	    public static String getClassRootPath()
	    {
	        URL url = WebPath.class.getClassLoader().getResource("");
	        String out = url.toString();// file:/C:/tomcat6/webapps/v/WEB-INF/classes/
	        out = out.substring(6);
	        return out;// C:/tomcat6/webapps/v/WEB-INF/classes/
	    }

	    
	    @SuppressWarnings("unchecked")
        public static String getClassPath(Class clz)
	    {
	        return getClassRootPath() + clz.getPackage().getName().replaceAll("\\.",
	                                                                          "/");
	    }


	    public static String getPreUrl(HttpServletRequest request)
	    {
	        return request.getHeader("Referer");
	    }


	    public static String getCurPage(HttpServletRequest request)
	    {
	        String url = request.getRequestURL().toString();
	        String path = request.getQueryString();
	        return url + "?" + path;
	    }
}
