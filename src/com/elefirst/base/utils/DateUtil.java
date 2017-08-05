package com.elefirst.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    /**
     * 将String型格式化,比如想要将2011-11-11格式化成2011年11月11日,就StringPattern("2011-11-11","yyyy-MM-dd","yyyy年MM月dd日").
     *
     * @param date       String 想要格式化的日期 "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"
     * @param oldPattern String 想要格式化的日期的现有格式
     * @param newPattern String 想要格式化成什么格式
     * @return String
     */
    public static String StringPattern(String date, String oldPattern, String newPattern) {
        if (date == null || oldPattern == null || newPattern == null)
            return "";
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern);        // 实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern);        // 实例化模板对象
        Date d = null;
        try {
            d = sdf1.parse(date);   // 将给定的字符串中的日期提取出来
        } catch (Exception e) {            // 如果提供的字符串格式有错误，则进行异常处理
            e.printStackTrace();       // 打印异常信息
        }
        return sdf2.format(d);
    }

    public static String[] getAllMonths(String from, String to) {
        String[] days = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            long toDay = format.parse(to).getTime();
            long fromDay = format.parse(from).getTime();

            long i = (toDay - fromDay) / (1000L * 60L * 60L * 24L * 30L);
            if ((toDay - fromDay) % (1000L * 60L * 60L * 24L * 30L) > (1000L * 60L * 60L * 24L * 15L)) {
                i = i + 1L;
            }
            i = i - 1;
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(format.parse(from));
            days = new String[Integer.valueOf(String.valueOf(i)) + 1];
            days[0] = from;
            for (int j = 1; j <= i; j++) {
                calendar.add(Calendar.MONTH, 1);
                days[j] = format.format(calendar.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取两个日期内所有日期
     *
     * @param from 开始日期
     * @param to   截止日期
     * @return 两个日期之间的所有日期
     */
    public static String[] getAllDays(String from, String to) {
        String[] days = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            long toDay = format.parse(to).getTime();
            long fromDay = format.parse(from).getTime();
            long i = (toDay - fromDay) / (1000L * 60L * 60L * 24L);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(format.parse(from));
            days = new String[Integer.valueOf(String.valueOf(i)) + 1];
            days[0] = from;
            for (int j = 1; j <= i; j++) {
                calendar.add(Calendar.DATE, 1);
                days[j] = format.format(calendar.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static String[] getAllHours(String from, String to) {
        String[] days = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            long toDay = format.parse(to).getTime();
            long fromDay = format.parse(from).getTime();
            long i = (toDay - fromDay) / (1000L * 60L * 60L);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(format.parse(from));
            days = new String[Integer.valueOf(String.valueOf(i)) + 1];
            days[0] = from;
            for (int j = 1; j <= i; j++) {
                calendar.add(Calendar.HOUR, 1);
                days[j] = format.format(calendar.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static String getDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        String[] hours = getAllHours("20170419000000", "20170420215959");
        for (int i = 0; i < hours.length; i++) {
            System.err.println(hours[i]);
        }
    }
}
