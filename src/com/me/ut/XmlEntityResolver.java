package com.me.ut;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class XmlEntityResolver implements EntityResolver
{
    static String emptyDtd = "";

    static ByteArrayInputStream byteIs = new ByteArrayInputStream(emptyDtd
            .getBytes());


    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException
    {
        return new InputSource(byteIs);
    }
}

// http://blog.csdn.net/lessoft/archive/2007/06/20/1659579.aspx
