package com.me.ut;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

    

public class MapUT
{

    /**
     * 把指定map中的指定字段，从Timestamps转换成String(yyyy-MM-dd)
     */
    public static void changTimestamps2String(Map map, String fieldName)
    {
        if (map.containsKey(fieldName))
        {
            Object obj = map.get(fieldName);

            if (obj instanceof Timestamp)
            {
                String datestr = DateTimeUT.getDateStringFromTimestamp(obj);
                map.put(fieldName, datestr);
            }
        }
    }


    /**
     * 
     * 从request中获得参数Map，并返回可读的Map
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getParameterMap(HttpServletRequest request)
    {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
       
        while (entries.hasNext())
        {
            String value = "";
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj)
            {
                value = "";
            }
            else if (valueObj instanceof String[])
            {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++)
                {
                    value += values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            }
            else
            {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }



    /**
     * 添加一列indexNum
     */
    @SuppressWarnings("unchecked")
    public static void insertIndexNum(List<Map> maps)
    {
        if (maps == null || maps.size() == 0)
        {
            return;
        }
        for (int i = 0; i < maps.size(); i++)
        {
            maps.get(i).put("indexNum", i + 1);
        }
    }
}
