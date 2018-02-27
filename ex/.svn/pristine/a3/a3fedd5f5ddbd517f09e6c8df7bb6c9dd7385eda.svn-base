package demo.common.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 使用动态代理模式
 * @author Administrator
 *
 */
public class RestHandler implements InvocationHandler{
	 //目标对象  
    private Object targetObject; 
    
    public RestHandler(Object targetObject) {
		this.targetObject = targetObject;
	}
    
    /* 
     * 下面方法是得到代理对象，如果得不到代理对象，这个效果也是没有作用的 
     * 最后一个参数是InvocationHandler接口，这也是为什么动态代理对象一定要实现这个接口的原因 
     * 得到的代理对象会执行invoke()方法 
     */  
    public Object newProxy(Object targetObject){  
        this.targetObject = targetObject;  
        //得到代理对象的方法，这个是反射机制里面的对象方法  
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),  
                                      targetObject.getClass().getInterfaces(),  
                                      this);  
    }  
    
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = method.invoke(this.targetObject, args); 
		return result;
	}

}
