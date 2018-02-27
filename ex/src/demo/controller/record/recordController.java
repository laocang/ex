package demo.controller.record;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.common.utils.GsonUtils;
import demo.domain.model.recordModel;
import demo.domain.model.userModel;
import demo.service.IRecordService;


@Controller
public class recordController {
	
	@Autowired
	private IRecordService recordService;
	
	@RequestMapping("record")
	public String record(HttpServletRequest request){
		String password = request.getParameter("password");
		return "record/index";
	}
	
	@RequestMapping("record/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		try{
			String logonId = request.getParameter("logonId");
			String password = request.getParameter("password");
			userModel user = recordService.login(logonId);
			if(user!=null&&!user.equals("")){
				if(user.getPasswd().equals(password)){
					recordService.updateLoginTime(user.getId());
					String userStr = GsonUtils.objToJson(user);
					Cookie cookie = new Cookie("user",URLEncoder.encode(userStr, "UTF-8"));
					response.addCookie(cookie);
					List<recordModel> records = recordService.getRecords(user.getId());
					request.setAttribute("records", records);
					return "record/list";
				}else{
					request.setAttribute("error", "密码错误！");
					return "record/index";
				}
			}else{
				request.setAttribute("error", "用户不存在！");
				return "record/index";
			}
		}catch(Exception e){
			request.setAttribute("error", "登录失败");
			return "record/index";
		}
	}
	
	@RequestMapping("record/add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		try{
			/*		String userId = "";
			Cookie[] userStr = request.getCookies();
			for(Cookie c : userStr){
				if(c.getName().equals("user")){
					userId = GsonUtils.jsonToBean(c.getValue(), userModel.class).getId();
				}
			}*/
			return "record/add";
		}catch(Exception e){
			request.setAttribute("error", "登录失败");
			return "record/index";
		}
	}
	
	@ResponseBody
	@RequestMapping("record/addrecord")
	public int addrecord(HttpServletRequest request,HttpServletResponse response){
		try{
			String userId = "";
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String date = request.getParameter("date");
			Cookie[] userStr = request.getCookies();
			for(Cookie c : userStr){
				if(c.getName().equals("user")){
					userId = GsonUtils.jsonToBean(URLDecoder.decode(c.getValue(),"UTF-8"), userModel.class).getId();
				}
			}
			int result = recordService.addRecord(userId, title, content, date);
			return result;
		}catch(Exception e){
			request.setAttribute("error", "添加失败");
			return 0;
		}
	}
}
