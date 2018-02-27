package demo.common.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.gson.JsonArray;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JdbcUtils {

	/**
	 * 传入的dbname为jdbc.properties的库名
	 * 按顺序传入map一一对应sql语句
	 */
	public static Object CommonUtil(String flowsn,String dbName,String sql,Map<String,String> map)
	{
	    Connection con = null;// 创建一个数据库连接
	    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	    ResultSet result = null;// 创建一个结果集对象
	    Object obj = null;
	    int sqlIndex = 1;
        JSONArray array = new JSONArray();// json数组  
	    try
	    {
	    	Properties pro = new Properties();
	    	pro.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
	        Class.forName(pro.getProperty(""+dbName+".jdbc.driverClassName"));
	        System.out.println("开始尝试连接数据库！");
	        String url = pro.getProperty(""+dbName+".jdbc.url");
	        String user = pro.getProperty(""+dbName+".jdbc.username");
	        String password = pro.getProperty(""+dbName+".jdbc.password");
	        con = DriverManager.getConnection(url, user, password);
	        System.out.println("连接成功！");
	        pre = con.prepareStatement(sql);// 实例化预编译语句
	        for (Map.Entry<String, String> entry : map.entrySet()) {
	        	 pre.setString(sqlIndex, entry.getValue());
	        	 sqlIndex++;
	        }
	        result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
	        // 获取列数  
	        ResultSetMetaData metaData = result.getMetaData();  
	        int columnCount = metaData.getColumnCount();  
	         
	        // 遍历ResultSet中的每条数据  
	         while (result.next()) {  
	             JSONObject jsonObj = new JSONObject();  
	              
	             // 遍历每一列  
	             for (int i = 1; i <= columnCount; i++) {  
	                 String columnName =metaData.getColumnLabel(i);  
	                 String value = result.getString(columnName);  
	                 jsonObj.put(columnName, value);  
	             }   
	             array.add(jsonObj);   
	         }  
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        try
	        {
	            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
	            // 注意关闭的顺序，最后使用的最先关闭
	            if (result != null)
	                result.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	            System.out.println("数据库连接已关闭！");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	    return array.toString();
	}
	
	
}
