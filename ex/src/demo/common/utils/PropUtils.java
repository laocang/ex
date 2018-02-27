package demo.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


/**
 * 获取属性文件Properties.properties的内容
 * @author Administrator
 *
 */
public class PropUtils {
	private Properties pro;
	
	public PropUtils(String filePath) {
		try {
			pro = new Properties();
//			String path = getClass().getProtectionDomain().getCodeSource().getLocation().getFile()+filePath;
			String path = this.getClass().getResource("/"+filePath).getPath();
//			File file = new File(path);
//			System.out.println(file.exists()+"判断文件是否存在："+file.getAbsolutePath());
//			System.out.println("这里进行获取路径："+path);
			ClassLoader cl = PropUtils.class.getClassLoader();
//			InputStream is = cl.getResourceAsStream(file.getAbsolutePath());
			InputStream is = new BufferedInputStream(new FileInputStream(path));
			pro.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getPropertyValue(String key){
		return pro.getProperty(key);
	}
	
//	public static void main(String[] args) {
//		PropUtils propUtils = new PropUtils("jdbc.properties");
//		String driverName = propUtils.getPropertyValue("jdbc.driverClassName");
//		String url = propUtils.getPropertyValue("jdbc.url");
//		String username = propUtils.getPropertyValue("jdbc.username");
//		String password = propUtils.getPropertyValue("jdbc.password");
//		System.out.println("这里进行获取数据信息："+driverName);
//	}
}
