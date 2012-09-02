package com.me.sysclient;

import java.io.StringReader;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Response;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;

import org.xml.sax.InputSource;

public class AsyncCallBackClient
{

	private static String payload = "<ns1:listPerson xmlns:ns1='http://services.cxf.me.com/'>"
			+ "		<username>zhang</username>" + "</ns1:listPerson>";

	@SuppressWarnings("restriction")
	public static class SimpleAsyncHandler implements AsyncHandler<Source>
	{
		public void handleResponse(Response<Source> responseSource)
		{
			try
			{
				System.out.println("callback-正在取得返回结果");
				Source response = responseSource.get();
				 Transformer transformer =
				 TransformerFactory.newInstance().newTransformer();
				 transformer.transform(response, new
				 StreamResult(System.out));
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception
	{
		// http://impl.services.cxf.me.com/ --表示命名空间,用的是反过来写的包名.
		// 第二个参数 服务名称: 实现类名字+Service
		QName serviceName = new QName("http://impl.services.cxf.me.com/",
				"PersonServiceService");
		Service service = Service
				.create(new URL(
								"http://127.0.0.1:8080/spring_cxf/services/personService?wsdl"),
						serviceName);

		// http://impl.services.cxf.me.com/ --表示命名空间,用的是反过来写的包名.
		// // 第二个参数 具体调用的方法: 实现类名字+Port
		QName portName = new QName("http://impl.services.cxf.me.com/",
				"PersonServicePort");

		// PAYLOAD :只传递body部分
		final Dispatch<Source> dispatch = service
				.createDispatch(portName, Source.class, Mode.PAYLOAD);

		final Source msg = new SAXSource(new InputSource(new StringReader(
				payload)));
		final AsyncHandler<Source> handler = new SimpleAsyncHandler();
		new Thread()
		{
			public void run()
			{
				dispatch.invokeAsync(msg, handler);
			}
		}.start();

		//此处一定要循环等待，因为可能后台服务器端还没有处理完毕，或者已经处理完毕，正在返回的过程中，客户端已经结束了．
		//所以可能会出现莫名其妙的错误。
		while (true)
		{
			Thread.sleep(1000);
			System.out.println("I'm doing other..");
		}
	}

}
