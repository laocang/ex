package demo.common.utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import oracle.sql.TIMESTAMP;

/**
 * 时间操作工具类
 * @author Administrator
 *
 */
public class DateUtils {
	public static final String YYYY_MM_DD_HHMMSS="yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD="yyyy-MM-dd";	
	/**
	 * 这里进行比较时间
	 * @param dateStr
	 * @param dateStrComplete
	 * @return 大于0的话 表示  时间1 > 时间2
	 */
	public static int completeDate(String dateStr , String dateStrComplete){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int result = 0;
		try {
			Date dateTime1 = dateFormat.parse(dateStr);
			Date dateTime2 = dateFormat.parse(dateStrComplete);
			result = dateTime1.compareTo(dateTime2); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static int completeDate(String leftDate , String rightDate,String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		int result = 0;
		try {
			Date dateTime1 = dateFormat.parse(leftDate);
			Date dateTime2 = dateFormat.parse(rightDate);
			result = dateTime1.compareTo(dateTime2); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 时间和字符串 转换
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = null; 
		if(date != null){
			dateStr = dateFormat.format(date);
		}
		return dateStr;
	}
	/**
	 * 日期转换为字符串设置格式
	 * @param date
	 * @return
	 */
	public static String dateToStrMatch(Date date){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = null; 
		if(date != null){
			dateStr = dateFormat.format(date);
		}
		return dateStr;
	}
	
	public static String dateToStrTime(Date date){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = null; 
		if(date != null){
			dateStr = dateFormat.format(date);
		}
		return dateStr;
	}
	/**
	 * 根据指定的格式进行将日期转换为字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStrFormat(Date date,String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		String dateStr = null; 
		if(date != null){
			dateStr = dateFormat.format(date);
		}
		return dateStr;
	}
	
	/**
	 * 字符串类型转换为日期类型的值
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Date date = null;  
        try {  
            date = format.parse(str);   
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
//        date = java.sql.Date.valueOf(str);  
        return date;  
    }  
	
	public static Date stringToTimeformat(String str) {  
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");  
        Date date = null;  
        try {  
            date = format.parse(str);   
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
//        date = java.sql.Date.valueOf(str);  
        return date;  
    }  
	
	/**
	 * 字符串类型转换为日期类型的值
	 * @param str
	 * @return
	 */
	public static Date stringToTime(String str) {  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date = null;  
        try {  
            date = format.parse(str);   
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
//        date = java.sql.Date.valueOf(str);  
        return date;  
    }  
	/**
	 * 日期转换为指定时间内容
	 * @param str
	 * @return
	 */
	public static Date stringToDateTime(String str) {  
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = null;  
		try {  
			date = format.parse(str);   
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
		return date;  
	}  
	/**
	 * 日期转换为时间
	 * @param str
	 * @return
	 */
	public static Date stringToDateBirth(String str) {  
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");  
		Date date = null;  
		try {  
			date = format.parse(str);   
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
		return date;  
	}  
	
	public static String dateTostrCheck(){
		 Date date = new Date();
		 DateFormat dateFormat = new SimpleDateFormat("ddHHmmss");
		 String dateStr = dateFormat.format(date);
		 return dateStr;
	}
	/**
	 * 日期转换为字符串格式转换
	 * @return
	 */
	public static String dateToStrCheckID(){
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
	/**
	 * 字符串转换为日期格式
	 * @param str
	 * @return
	 */
	public static Date stringToDateCheckID(String str) {  
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");  
        Date date = null;  
        try {  
            date = format.parse(str);   
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
//        date = java.sql.Date.valueOf(str);  
        return date;  
    }  
	/**
	 * 当前时间添加分钟
	 * @return
	 */
	public static Date dateAddMinute(int minute){
		  DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");  
		  Calendar nowTime = Calendar.getInstance();
		  nowTime.add(Calendar.MINUTE, minute);
		  return nowTime.getTime();
	}
	
	/**
	 * 比较日期的大小
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static int compareToDateSize(Date dt1, Date dt2) {
		try {
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 格式化时间
	 * @param date 时间
	 * @param pattern 格式
	 * @return
	 */
	public static String formatString(String date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
		try {
			Date newDate = sdf.parse(date);
			return sdf.format(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	    return null;
	}
	
	public static String timestampToString(Timestamp timestamp,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
		return sdf.format(timestamp);
	}
	
	public static String getCurrentDate(String pattern){
		SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
		return df.format(new Date());
	}
	
	public static boolean isValidDate(String str) {
	      boolean convertSuccess=true;
	      // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
	       SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	       try {
	    	   // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
	          format.setLenient(false);
	          format.parse(str);
	       } catch (ParseException e) {
	    	   // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
	           convertSuccess=false;
	       } 
	       return convertSuccess;
	}
	
	public static String add(String beginDate,long days){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = dateFormat.parse(beginDate); 
			long l =  24*60*60*days+date.getTime()/1000;
			String result= dateFormat.format(l*1000);
			return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Timestamp 转换为 String
	 * @param time
	 * @return
	 */
	public static String timeStampToString(Timestamp time){
        String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);   
        try {   
            //方法一   
            tsStr = sdf.format(time);   
            System.out.println(tsStr);   
            //方法二   
//            tsStr = time.toString();   
//            System.out.println(tsStr);   
        } catch (Exception e) {   
            e.printStackTrace();   
        }  
        return tsStr;
	}
	/**
	 * 这里进行转换 Timestamp
	 * @param str
	 * @return
	 */
	public static Timestamp strToTimestamp(String str) {
		  if(null != str && !"".equals(str)){
		    Date date = stringToDateTime(str);
		    return new Timestamp(date.getTime());
		  }
		  return null;
	}
	
	 /** 
     * 根据oracle的Timestamp获取字符串日期时间 
     * @param t Timestamp时间 
     * @param formatStr 格式化字符串，如果是null默认yyyy-MM-dd hh:mm:ss 
     * @return 格式化后的字符串 
     */  
    public static String getDateBySqlTimestamp(TIMESTAMP timestamp) {  
        try {  
            Timestamp tt;  
            tt = timestamp.timestampValue();  
            Date date = new Date(tt.getTime());  
            SimpleDateFormat sd = new SimpleDateFormat(YYYY_MM_DD);  
            return sd.format(date);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
}
