package lc.watchshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lc.dinner.service.IDinnerService;
import lc.watchshop.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class watchshopController {

	@Autowired
	private IOrderService orderServiceImpl;
	
	@RequestMapping("watchshop/main")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "watchshop/main";
	}
	
	@ResponseBody
	@RequestMapping("addOrder")
	public String addOrder(HttpServletRequest request,HttpServletResponse response){
		String product =request.getParameter("product");
		String name =request.getParameter("name");
		String tel =request.getParameter("tel");
		String city =request.getParameter("city");
		String area =request.getParameter("area");
		String addr =request.getParameter("addr");
		String price =request.getParameter("price");
		String payway =request.getParameter("payway");
		String privince=request.getParameter("privince");
		String beizhu=request.getParameter("beizhu");
		String result = orderServiceImpl.addOrder(product,name,tel,city,area,addr,price,payway,privince,beizhu);
		return result;
	}
}
