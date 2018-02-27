package lc.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtils {
	private static BasicDataSource dataSource = null;
     
     public static void init(){
       //判断数据库连接是否已经分配，如果已分配，则关闭
         if(dataSource != null){
            try {
               dataSource.close();
             } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             dataSource = null;
         }
        
        try {
             //配置，使用Properties,只接受这一种方式
             Properties p = new Properties();
             p.setProperty("driverClassName","com.mysql.jdbc.Driver");
             p.setProperty("url", "jdbc:mysql://localhost:3306/sampledb");
             p.setProperty("username", "root");
             p.setProperty("password", "yflllb");
             
             dataSource= (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
         } catch (Exception e) {
            // TODO: handle exception
         }
     }
     
    public static synchronized Connection getConnection() throws SQLException {
         if(dataSource == null){
             init();
         }
         Connection connection = null;
         if(dataSource != null){
             connection = dataSource.getConnection();
         }
         
         return connection;
     }
 }