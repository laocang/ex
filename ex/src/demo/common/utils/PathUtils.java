package demo.common.utils;

public class PathUtils {
	public static String getWebAppsPath(){
		String path=PathUtils.class.getResource("/").toString();
		path = path.substring(6, path.indexOf("webapps")+7);
		return path;
	}
}
