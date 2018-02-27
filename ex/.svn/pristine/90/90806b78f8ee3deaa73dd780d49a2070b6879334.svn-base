package demo.domain.base;

import java.util.Properties;

import javax.sql.DataSource;


import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.reflection.MetaObject;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

public class BonecpDataSourceFactory implements DataSourceFactory{
	private DataSource boneCPDataSource = null;
	private  BoneCPConfig config = null;
	public BonecpDataSourceFactory() {
		config = new BoneCPConfig();
		boneCPDataSource = new BoneCPDataSource(config);
	}
	
	public DataSource getDataSource() {
		return boneCPDataSource;
	}
	
	public void setProperties(Properties properties) {
		
		Properties driverProperties = new Properties();
	    MetaObject metaDataSource = MetaObject.forObject(boneCPDataSource);
	    for (Object key : properties.keySet()) {
	    	  String propertyName = (String) key;
	    	// 利用反射技术,初始化datasource属性的值
              if (metaDataSource.hasSetter(propertyName)) {
                  String value = (String) properties.get(propertyName);
                  Object convertedValue = convertValue(metaDataSource, propertyName, value);
                  metaDataSource.setValue(propertyName, convertedValue);
              }
	    	
	    }
	}
	
	 private Object convertValue(MetaObject metaDataSource, String propertyName,
	            String value) {
	        Object convertedValue = value;
	        Class targetType = metaDataSource.getSetterType(propertyName);
	        if (targetType == Integer.class || targetType == int.class) {
	            convertedValue = Integer.valueOf(value);
	        } else if (targetType == Long.class || targetType == long.class) {
	            convertedValue = Long.valueOf(value);
	        } else if (targetType == Boolean.class || targetType == boolean.class) {
	            convertedValue = Boolean.valueOf(value);
	        }
	        return convertedValue;
	    }

	
}
