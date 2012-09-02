package com.me.ut;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;


public class FileUT
{

    public static boolean exist(String filePath)
    {
        return new File(filePath).exists();
    }

     


    /**
     * 指定的objid下是否含有文件夹以及文件夹下含有文件
     * true表示含有文件夹和文件
     */
    public static boolean isFolderAndHasFile(String fileFold)
    {
        if (isFolder(fileFold))
        {
            if ((new File(fileFold)).list().length > 0)// 文件夹下面含有文件
            {
                return true; // 指定的文件夹下含有文件
            }
            else
            {
                return false;// 指定的文件夹下没有文件
            }
        }
        return false;
    }


    public static boolean isFolder(String fileFold)
    {
        if (exist(fileFold) && (new File(fileFold)).isDirectory())
        {
            return true;
        }
        return false;
    }


    @SuppressWarnings({ "unchecked" })
    public static String getFirstFileName(String fileFold)
    {
        // 应该是取得修改时间最晚的一个文件
        String[] fileNames = (new File(fileFold)).list();

        Hashtable hash = new Hashtable();
        for (int i = 0; i < fileNames.length; i++)
        {
            String fileName = fileNames[i];
            String f = fileFold + File.separatorChar + fileName;
            if (new File(f).exists())
            {
                File file = new File(f);
                hash.put(fileName,
                         file.lastModified());
            }
        }
        Enumeration<String> keys = hash.keys();

        while (keys.hasMoreElements())
        {
            String key = (String) keys.nextElement();
            long lastmodifyDate = Long.valueOf(hash.get(key).toString());

            if (keys.hasMoreElements())
            {
                String key2 = (String) keys.nextElement();
                long lastmodifyDate2 = Long.valueOf(hash.get(key2).toString());
            }

        }

        String file = ((new File(fileFold)).list())[0];
        return file;
    }

      
}
