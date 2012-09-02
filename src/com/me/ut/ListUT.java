package com.me.ut;


import java.util.ArrayList;
import java.util.List;


public class ListUT
{

    @SuppressWarnings("unchecked")
    public static List cutByPage(List list, Pager pager)
    {
        List listOut = new ArrayList();
        if (list == null || list.size() == 0)
        {
            return list;
        }
        if (pager == null)
        {
            return list;
        }

        int pageNo = pager.getPageNumber();// 查询第几页
        int pageSize = pager.getPageSize();// 每页几条记录

        // 依据pageSize的取值情况判断所有异常
        if (pageSize <= 0)
        {
            throw  new  RuntimeException("没有指定分页大小");
        }
        if (pageSize >= list.size())
        {
            // return list;
            if (pageNo == 1)
            {
                return list;
            }
            if (pageNo > 1)
            {
                return new ArrayList();
            }
        }
        if (pageSize > 0 && pageSize < list.size())
        {
            // 取得开始的index和结束的index ,注：所有关于分页的index都从1开始

            if (pageNo <= 0)
            {
                throw new  RuntimeException("没有指定页码");
            }

            if (pageNo > 0)
            {

                // 取得 beginIndex
                int beginIndex = ((pageNo - 1) * pageSize) + 1;//
               
                if (beginIndex > list.size())
                {
                    return new ArrayList();
                }
                
                int endIndex = pageNo * pageSize;

                if (endIndex >= list.size())
                {
                    endIndex = list.size();
                }
                listOut = list.subList(beginIndex - 1, endIndex);// 包括end不包括begin
            }
        }
        return listOut;
    }
}
