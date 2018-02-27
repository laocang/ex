package demo.domain.base;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import demo.common.utils.LogUtils;
import demo.domain.enums.DataSourceEnvironment;



public class MySQLBaseRepository {
	//指定MyBatis配置文件  
    private final String RESOURCE = "mybatis-config.xml";
    
    /*
     * 获取数据库访问链接
     */
	public SqlSession getSqlSession(){
		 //1、指定MyBaties配置文件  
		InputStream inputStream = null;  
        SqlSessionFactory sessionFactory = null; 
        SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(RESOURCE);  
			 //2、创建SqlSessionFactory()  
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream,DataSourceEnvironment.development.name());  
	        session = sessionFactory.openSession();
		} catch (IOException e) {
			LogUtils.error(e);
			e.printStackTrace();
		}finally{
		  if(inputStream != null){
	          	try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	          }
		}    
		return session;
	}
	
	  /*
     * 关闭数据库访问链接
     */
    public static void closeSession(SqlSession session) {
        if(session != null){
        	session.close();
        }
    }
    
}
