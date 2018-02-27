package demo.controller.love;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lc.dinner.dao.IDinnerDao;
import lc.dinner.model.userModel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.dao.ITestDao;

@Controller
public class loveController {
	
	@Autowired
	private ITestDao testDao;
	
	@RequestMapping("love")
	public String index(HttpServletRequest request,HttpServletResponse response){
		Date date = new Date();
		String ip = request.getRemoteAddr();
		return "love/love";
	}
	
	@RequestMapping("fy")
	public String fy(HttpServletRequest request,HttpServletResponse response){
		return "love/fy";
	}
	
	
	@RequestMapping("love/pic")
	public String pic(HttpServletRequest request,HttpServletResponse response){
		return "love/pic";
	}

	
	@RequestMapping("test/index")
	public String eatIndex(HttpServletRequest request){
		List<userModel> users = testDao.getUser();
		return "dinner/index";
	}
	
}
