package demo.domain.base;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import demo.common.utils.CookieUtils;
import demo.domain.log.LogProvider;


public class BaseRoot {
	public Logger getLogger(String system) {
		return LogProvider.getLogger(this.getClass(), system);
	}

	public Logger getLogger() {
		return LogProvider.getLogger(this.getClass());
	}
	
	public  String sessionName = "Redis_SessionId";
	 /**
	  * 这里进行生成SessionID的值
	  * @param request
	  * @param response
	  * @return
	  */
	public String GetSessionID(HttpServletRequest request,HttpServletResponse response){
		 Cookie cookie = CookieUtils.getCookieByName(request, sessionName);
       if (cookie == null || cookie.getValue() == null || "".equals(cookie.getValue())){
           UUID uuid = UUID.randomUUID(); //用它可以产生一个号称全球唯一的ID     
           String newSessionID = uuid.toString();
           CookieUtils.addCookie(response, sessionName, newSessionID);
           return "Session_"+newSessionID;
       }else{
           return "Session_"+cookie.getValue();
       }
   }
	/**
	 * 存放验证码的值
	 * @param request
	 * @param response
	 * @return
	 */
	public String getSessionCodeId(HttpServletRequest request,HttpServletResponse response,String cookieName){
		Cookie cookie = CookieUtils.getCookieByName(request, cookieName);
		if (cookie == null || cookie.getValue() == null || "".equals(cookie.getValue())){
			UUID uuid = UUID.randomUUID(); //用它可以产生一个号称全球唯一的ID     
			String newSessionID = uuid.toString();
			CookieUtils.addCookie(response, cookieName, newSessionID);
			return "CodeSession_"+newSessionID;
		}else{
			return "CodeSession_"+cookie.getValue();
		}
	}
   /**
    * 移除所存放的SessionID的值	
    * @param response
    */
   public void removeCookieSession(HttpServletResponse response){
	   CookieUtils.removeCookie(response, sessionName);
   } 
   
}
