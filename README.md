﻿
概述：

示例意图

cxf结合spring实现webserver的发布。
 以及如何通过java代码调用，包括异步调用方式。

本示例虽然通过maven构建，但是同样也上传了lib下所有的jar。
开发环境为:MyEclipse 8.5



1:
-------------------------------------------------

查看wsdl的地址
http://127.0.0.1:8080/spring_cxf/services

其中"services" 是配置在web.xml里的

----------

2:
-------------------------------------------------

查看工具 可以使用 soupUI
拷贝类似下面的地址 
http://127.0.0.1:8080/spring_cxf/services/personService?wsdl

使用soapUI即可调用已经部署好的webservices
soapUI  3.5 [破解]

下载地址 http://pan.baidu.com/share/link?shareid=12202&uk=554011874



3：
-------------------------------------------------
使用java 代码调用的方式

查看
包 com.me.client
类  WSClientTest

所以如果客户端处于另外的项目中，另外的项目中必须有相同的接口。
相同的参数类。

动态化的webservice还没有研究过。


4：
----------------------------------------------------
异步方式调用
一共有两种方式：

Polling  --- 客户端不停循环检测

CallBack --- 服务器端自动回调

com.me.sysclient


 






 
