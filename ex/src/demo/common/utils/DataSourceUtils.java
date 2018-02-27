package demo.common.utils;

import demo.domain.enums.DataSourceEnvironment;

/**
 * 这里进行配置数据源的内容
 * @author Administrator
 *
 */
public class DataSourceUtils {
	
	/**
	 * 这里进行修改数据源的配置内容
	 * @return
	 */
	public static DataSourceEnvironment getDataSource(){
		PropUtils propUtils = new PropUtils(Contants.PROPERFILE);
		String db = propUtils.getPropertyValue("tlw.datasource");
//		System.out.println("这里获取的数据源的内容："+db);
		return DataSourceEnvironment.development;
		
	}
	
	public static DataSourceEnvironment getDataSource(String dbName){
		return DataSourceEnvironment.valueOf(dbName);
	}
}
