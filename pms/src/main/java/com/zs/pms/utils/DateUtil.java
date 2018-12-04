package com.zs.pms.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
public static void showToday(){
	//日历类，获得当前时间，使用获得实例的方法来获得方法
	Calendar cal=Calendar.getInstance();
	//获得当前年
	int y=cal.get(Calendar.YEAR);
	//获得月
	int m=cal.get(Calendar.MONTH);
	//获得日
	int d=cal.get(Calendar.DATE);
	//获得小时(12小时)
	int hh=cal.get(Calendar.HOUR);
	//获得小时(24小时)
	int hh1=cal.get(Calendar.HOUR_OF_DAY);
	//获得分钟
	int mm=cal.get(Calendar.MINUTE);
	//获得秒
	int ss=cal.get(Calendar.SECOND);
	//输出语句
	System.out.println(y+"-"+m+"-"+d+" "+hh1+":"+mm+":"+ss);
	//增加时间（给日历哪个属性加值，增加多少）
	cal.add(Calendar.MONTH, -2);
	//重新给月赋值
	m=cal.get(Calendar.MONTH)+1;
	//重新给年赋值
	y=cal.get(Calendar.YEAR);
	//输出语句
	System.out.println(y+"-"+m+"-"+d+" "+hh1+":"+mm+":"+ss);
}
/**
 * 把Date转化成字符串数据的方法
 * @param time 需要转化的Date类书籍
 * @param pattern 字符串格式：yy-MM-dd HH:mm:ss   y年 M月 d日 H 24小时制时间 h 12小时制时间 m分 s秒
 * @return 转化后的字符串
 */
public static String getDateToString(Date time,String pattern){
	//创建一个日期格式化的小对象，构造函数（格式化成什么样子）
	DateFormat df=new SimpleDateFormat(pattern);
	//调用format方法可以把date数据转化成字符串数据
	return df.format(time);
}
/**
 * 把字符串转化Date数据的方法
 * @param time 需要转化的字符串类型数据
 * @param pattern 字符串格式：yy-MM-dd HH:mm:ss   y年 M月 d日 H 24小时制时间 h 12小时制时间 m分 s秒
 * @return 转化后的Date数据
 * @throws ParseException 因为字符串不一定能转成时间所以可能会产生异常
 */
public static Date getStrToDate(String time,String pattern) throws ParseException{
	//创建一个日期格式化的对象，构造函数
	DateFormat df=new SimpleDateFormat(pattern);
	//调用parse的方法可以把字符串数据转化Date类
	return df.parse(time);
}	

}
