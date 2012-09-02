package com.me.ut;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateTimeUT
{
    /**
     * 字符转转换成Date对象,输入格式必须是：yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static Date getDateTime(String dateStr)
    {
        if (null == dateStr || dateStr.length() <= 0)
        {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = formatter.parse(dateStr, new ParsePosition(0));
        return d;
    }


    /**
     * 字符转转换成Date对象,输入格式必须是：yyyy-MM-dd
     * 
     * @return
     */
    public static Date getDate(String dateStr)
    {
        if (null == dateStr || dateStr.length() <= 0)
        {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d = formatter.parse(dateStr, new ParsePosition(0));
        return d;
    }


    /**
     * 判断日期格式是否是 yyyy-MM-dd <br>
     * 不兼容 yyyy-M-d
     */
    public static boolean validDateFormat(String datestr)
    {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date date = (Date) formatter.parse(datestr);
            return datestr.equals(formatter.format(date));
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public static String getDateTimeString(Date date)
    {
        if (date == null)
        {
            return null;
        }

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(date);

    }


    /**
     * 
     * 返回Datetime的日期部分(String)
     * 
     * @param date
     * @return
     */
    public static String getDateString(Date date)
    {
        if (date == null)
        {
            return null;
        }

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);
    }


    /**
     * 
     * 返回Datetime的日期和部分(String)
     * 
     * @param date
     * @return
     */
    public static String getDatetimeString(Date date)
    {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(date);
    }


    /**
     * 取得当前时间
     * 
     * @return
     */
    public static Date getNow()
    {
        return new Date();
    }


    /**
     * 
     * 取出日期部分，返回日期不分的字符串 输入参数可以使Timestamp或者String(yyyy-MM-dd HH:mm:ss)
     * 
     * @param datetime
     * @return
     */
    public static String getDateStringFromTimestamp(Object datetime)
    {
        if (datetime instanceof Timestamp)
        {
            Date date = new Date(((Timestamp) datetime).getTime());
            return getDateString(date);
        }
        else if (datetime instanceof String)
        {
            Date date = getDateTime((String) datetime);
            return getDateString(date);
        }
        throw new RuntimeException(
                "输入参数只能是Timestamp或者String(yyyy-MM-dd HH:mm:ss)");
    }


    /**
     * 返回格式如：2012年7月10日
     * 
     * @return
     */
    public static String getLocalNow()
    {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy年M月d日");
        return f.format(date);
    }


    /**
     * 取得下一天[排除节假日] <br>
     * <br>
     * 返回格式 yyyy-MM-dd
     */
    public static String getNextDayNotVacation(Date date)
    {
        int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nextDate = dateFormat.format(date.getTime() + MILLIS_IN_DAY);

        // 是否是节假日,如果是继续next
        if (isVacationOrHoliday(nextDate))
        {
            nextDate = getNextDayNotVacation(getDate(nextDate));
            return nextDate;
        }
        return nextDate;
    }


    /**
     * 
     * 是否是节假日
     * 
     * @return
     */
    private static boolean isVacationOrHoliday(String str)
    {
        Date date = getDate(str);
        if (getWeekday(date) == 6 || getWeekday(date) == 7)
        {
            return true;
        }
        if (getHolidays().contains(str))
        {
            return true;
        }
        return false;
    }


    /**
     * 取得指定的时间是周几
     * 
     * 周六是7，周日是1 ,周一是2 [所以设置的是]
     */
    public static int getWeekday(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0)
        {
            dayOfWeek = 7;
        }
        System.out.println("周几：" + dayOfWeek);
        return dayOfWeek;
    }


    /**
     * 返回当前中的所有节假日
     */
    public static List<String> getHolidays()
    {
        List<String> lists = new ArrayList<String>();

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        System.out.println(year);

        // 格式写成 yyyy-MM-dd
        String s = year + "-" + "05" + "-" + "01";
        String s1 = year + "-" + "10" + "-" + "01";

        lists.add(s);
        lists.add(s1);

        return lists;
    }


    public static void main(String[] args)
    {
        DateTimeUT ut = new DateTimeUT();

        System.out.println(ut.validDateFormat("1988-05-05"));

    }


    public static Timestamp getNowstamp()
    {
        Timestamp time=new Timestamp(new Date().getTime());
        return time;
    }
    
    public static Timestamp getNowstamp(Date d)
    {
        Timestamp time=new Timestamp(d.getTime());
        return time;
    }
}
