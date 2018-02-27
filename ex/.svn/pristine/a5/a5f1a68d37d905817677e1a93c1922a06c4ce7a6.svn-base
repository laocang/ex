package demo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日历工具类
 * 
 * @author Administrator
 * 
 */
public class CalendarUtils {
	/**
	 * 获取今天的年月日的方法
	 */
	public static String getDay() {
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate(); // 日
		return year +"-"+month+"-"+day;
	}

	private static final int FIRST_DAY = Calendar.MONDAY;

	/**
	 * 设置当前日期为这周的第一天
	 * @param calendar
	 */
	public static void setToFirstDay(Calendar calendar) {
		while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
			calendar.add(Calendar.DATE, -1);
		}
	}

	/**
	 * 设置当前日期为这周的最后一天
	 * @param calendar
	 */
	public static void setToLastDay(Calendar calendar) {
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	}
	
	/**
	 * 设置指定的年月日 日期时间
	 * @param calendar
	 * @param year
	 * @param month
	 * @param day
	 */
	public static void setDesigatDay(Calendar calendar,String year,String month,String day) {
		calendar.set(Calendar.YEAR,Integer.parseInt(year));
		calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
	}
	
	
	/**
	 * 获取指定格式的日期时间内容
	 * @param calendar
	 * @return
	 */
	public static String getDateTime(Calendar calendar){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年-MM月-dd日");
		String date = dateFormat.format(calendar.getTime());
		return date;
	}
	/**
	 * 获取规定的格式的时间 这里进行加一天内容
	 * @param calendar
	 * @return
	 */
	public static String getDatePareseTime(Calendar calendar){
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(calendar.getTime());
		return date;
	}
	
	/**
	 * 获取规定格式时间的进行加一天
	 * @param calendar
	 * @return
	 */
	public static String getTomorrowDataToString(Calendar calendar){
		 calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		 Date date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String dateString = formatter.format(date);
		 return dateString;
	}
	
	/**
	 * 不加一天的日期信息
	 * @param calendar
	 * @return
	 */
	public static String getDateTimecal(Calendar calendar){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(calendar.getTime());
		return date;
	}
	
	/**
	 * 获取指定时间的星期日期时间
	 * @param calendar
	 * @return
	 */
	public static String getWeekDay(Calendar calendar){
		SimpleDateFormat dateFormat = new SimpleDateFormat("EE");
		String week = dateFormat.format(calendar.getTime());
		return week;
	}
	
	/**
	 * 获取一周中的第一天 进行添加6天 变成最后一天的内容信息
	 * @param firstDate
	 * @return
	 */
	public static String getLastDayWeekTime(String firstDate){
		String lastDate = "";
		try {
			Date date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(firstDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 6);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			lastDate  = dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastDate;
		
	}
	
    /**
    * 根据日期计算属于第几周
    * @param date 格式 yyyy-MM-dd
    * @throws ParseException
    */
   public static int getWeekOfYear(String date){
       try {
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
           Calendar cal = Calendar.getInstance();
           cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
           //cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
           cal.setMinimalDaysInFirstWeek(1); // 设置每周最少为1天
           cal.setTime(df.parse(date));
           return cal.get(Calendar.WEEK_OF_YEAR);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return 0;
   }

	
	/** 
     * 获取某年某周的最后一天 
     * @param:@param year  所传入年
     * @param:@param week  所传入第几周
     * @return:String 
     */ 
    public static String getLastDayOfWeek(int year,int week){
        Calendar cal = Calendar.getInstance();  
        //设置年份  
        cal.set(Calendar.YEAR,year);  
        //设置周  
        cal.set(Calendar.WEEK_OF_YEAR, week);  
        //设置该周第一天为星期一  
        cal.setFirstDayOfWeek(Calendar.MONDAY);   
        //设置最后一天是星期日  
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6); // Sunday  
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String lastDayOfWeek = sdf.format(cal.getTime());  
           
        return lastDayOfWeek;  
    }  
	
    /**
     * 打印日期信息
     * @param calendar
     */
    public static void printDay(Calendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年-MM月-dd日  EE");
		System.out.println(dateFormat.format(calendar.getTime()));
	}

	public static void main(String[] args) {
		String date = "2015-1-1";
		int week = getWeekOfYear(date);
		System.out.println("获取"+week);
		
	}

}
