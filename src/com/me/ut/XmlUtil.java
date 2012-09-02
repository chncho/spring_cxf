package com.me.ut;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;


public class XmlUtil
{

    private static String sysbasic_configFilePath = WebPath.getClassRootPath() + "configs/sysbasic.xml";

    private XmlUtil()
    {

    }


    public static XmlUtil getInstance()
    {
        return XmlUtilHoler.xml;
    }


    private static class XmlUtilHoler
    {
        public static XmlUtil xml = new XmlUtil();
    }


    /**
     * 取得一系列的值
     * 
     * @xpath
     * @param xmlfilePath
     *            相对于classes路径
     * @return
     */
    public List<String> getValues(String xpath)
    {
        List<String> list = getPaths(xpath,
                                     sysbasic_configFilePath);
        return list;
    }


    /**
     * 取得某一个值
     * 
     * @xpath
     * @param xmlfilePath
     *            相对于classes路径
     * @return
     */
    public String getValue(String xpath)
    {
        String out = getPath(xpath,
                             sysbasic_configFilePath);
        return out;
    }


    public static void main(String[] args)
    {
        String password = XmlUtil.getInstance().getValue("sys");
        System.out.println(password);
        System.out.println("");
    }


    private synchronized String getPath(String xpath,
                                       String ConfigPath)
    {
        String out = "";
        SAXReader reader = new SAXReader();
        FileInputStream filein = null;
        InputStreamReader readerin = null;
        Document document = null;
        try
        {
            filein = new FileInputStream(new File(ConfigPath));
            reader.setEntityResolver(new XmlEntityResolver());
            readerin = new InputStreamReader(filein, "UTF-8");
            document = reader.read(readerin);
            Node node = document.selectSingleNode(xpath);
            if (node == null)
            {
                throw new RuntimeException("sys.xml配置错误");
            }
            out = node.getStringValue();
        }
        catch (Exception e)
        {
            System.out.println(xpath);
            System.out.println(ConfigPath);
            e.printStackTrace();
        }
        finally
        {
            if (null != filein)
            {
                try
                {
                    filein.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (null != readerin)
            {
                try
                {
                    readerin.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return out.trim();
    }


    private synchronized List<String> getPaths(String xpath,
                                              String ConfigPath)
    {
        List<String> listOut = new ArrayList<String>();
        try
        {
            SAXReader reader = new SAXReader();
            reader.setEntityResolver(new XmlEntityResolver());
            FileInputStream filein = new FileInputStream(new File(ConfigPath));
            InputStreamReader readerin = new InputStreamReader(filein, "UTF-8");
            Document document = reader.read(readerin);
            List<Node> nodes = document.selectNodes(xpath);
            if (nodes == null)
            {
                throw new RuntimeException(ConfigPath + "配置错误");
            }

            for (int i = 0; i < nodes.size(); i++)
            {
                if (StringUT.isEmpty(nodes.get(i)))
                {
                    continue;
                }
                listOut.add(nodes.get(i).getStringValue());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listOut;
    }


    @SuppressWarnings("unchecked")
	public synchronized List<Node> getPathNodes(String xpath,
                                                String ConfigPath)
    {
        List<Node> nodes = new ArrayList<Node>();
        try
        {
            SAXReader reader = new SAXReader();
            reader.setEntityResolver(new XmlEntityResolver());
            FileInputStream filein = new FileInputStream(new File(ConfigPath));
            InputStreamReader readerin = new InputStreamReader(filein, "UTF-8");
            Document document = reader.read(readerin);
            nodes = document.selectNodes(xpath);
            if (nodes == null)
            {
                throw new RuntimeException(ConfigPath + "配置错误");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return nodes;
    }

    
    @SuppressWarnings("unchecked")
    public int getCount(String xpath,
                        String ConfigPath)
    {
        List list = getPathNodes(xpath,
                                 ConfigPath);
        return list.size();

    }

}
