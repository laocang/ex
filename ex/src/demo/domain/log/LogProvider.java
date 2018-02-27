package demo.domain.log;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class LogProvider{
	public static Logger getLogger(Class<?> clazz) {
		return Logger.getLogger(clazz);
	}
	
	public static Logger getLogger(Class<?> clazz,String system) {
		MDC.put("system", system);
		return getLogger(clazz);
	}
}
