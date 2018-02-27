package demo.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器
 *  preHandle：拦截器的前端，执行控制器之前所要处理的方法，通常用于权限控制、日志，其中，Object o表示下一个拦截器；
	postHandle：控制器的方法已经执行完毕，转换成视图之前的处理；
	afterCompletion：视图已处理完后执行的方法，通常用于释放资源；
 * @author Administrator
 *
 */
public class MyInterceptor extends HandlerInterceptorAdapter{
	/**
	 * 最后执行，可用于释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 显示视图前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println(request.getContentType()+"-----"+request.getCharacterEncoding()+"------"+request.getContextPath());
		System.out.println("MyInterceptor.postHandle()---viewName:"+modelAndView.getViewName());
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * Controller之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String url = request.getRequestURI();
		
		System.out.println("MyInterceptor.preHandle()"+url);
		
		return super.preHandle(request, response, handler);
	}
}
