package demo.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import demo.domain.base.BaseRoot;



/**
 * 利用反射调用方法
 * @author Administrator
 *
 */
public class ReflectMethodUtils extends BaseRoot{
	/**
	 * 根据反射进行调用相对应的方法的信息
	 * @param method 方法名称
	 * @param types 方法所带参数的类型
	 * @param parames 方法所参数对应的值
	 * @return
	 */
	public String getMethods(String clazzName,String method,Class types[],Object[] parames){
		  Object result = null;
		  try {
			String className = clazzName;
			Class clazz = Class.forName(className);
			Method mainMethod = clazz.getMethod(method,types);
			  //使用默认构造函数获取对象
	        Object invokeTester = clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
	        //通过反射调用方法  
		    result =  mainMethod.invoke(invokeTester,parames);
		    //打印日志
			getLogger().info("调用方法："+method+"获取返回的值："+result);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result==null?null:result.toString();
	}
}
