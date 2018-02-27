package demo.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这里进行对Cookie的使用操作类
 * @author Administrator
 *
 */
public class CookieUtils {
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletResponse response,String name,String value){
	    Cookie cookie = new Cookie(name,value);
	    //设置路径，这个路径即该工程下都可以访问该cookie 
	    //如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
	    cookie.setPath("/");
	    cookie.setMaxAge(60*60*6); //这里设置 Cookie保存20分钟后失效
	    response.addCookie(cookie);
	}
	
	public static void removeCookie(HttpServletResponse response,String name){
	    Cookie cookie = new Cookie(name,null);
	    //设置路径，这个路径即该工程下都可以访问该cookie 
	    //如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
	    cookie.setPath("/");
	    cookie.setMaxAge(0); //这里设置 Cookie保存30天后失效
	    response.addCookie(cookie);
	}
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	public static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
}
