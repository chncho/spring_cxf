package com.me.sysclient;

import java.io.StringReader;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Response;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;

import org.xml.sax.InputSource;

public class AsyncPollingClient {
	
	
	//payload中的格式可以通过wsdl看出来
	private static String payload =
					"<ns1:listPerson xmlns:ns1='http://services.cxf.me.com/'>"+
					"		<username>zhang</username>"+
					"</ns1:listPerson>";
	//下面不明确的地方可以查看wsdl确定
	@SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception {
		// http://impl.services.cxf.me.com/  --表示命名空间,用的是反过来写的包名.
		// 第二个参数  服务名称:  实现类名字+Service
		QName serviceName = new QName("http://impl.services.cxf.me.com/", "PersonServiceService");
		Service service = Service.create(new URL("http://127.0.0.1:8080/spring_cxf/services/personService?wsdl"), serviceName);
		
		// http://impl.services.cxf.me.com/  --表示命名空间,用的是反过来写的包名.
		// // 第二个参数 具体调用的方法:  实现类名字+Port
		QName portName = new QName("http://impl.services.cxf.me.com/", "PersonServicePort");
		
		// PAYLOAD :只传递body部分
		Dispatch<Source> dispatch = service.createDispatch(portName, Source.class, Mode.PAYLOAD);
		
		Source msg = new SAXSource(new InputSource(new StringReader(payload)));
		Response<Source> responseSource = dispatch.invokeAsync(msg);
		
		//下面等待一分钟假装正在处理别的事情
		System.out.println("Sleep...");
		Thread.sleep(1000);
		System.out.println("Wake up...");
		
		//之后又来看一下是否取到了返回结果．
		Source response = responseSource.get();//这个地方会始终等待!!!跟同步没区别
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(response, new StreamResult(System.out));
	}
}
