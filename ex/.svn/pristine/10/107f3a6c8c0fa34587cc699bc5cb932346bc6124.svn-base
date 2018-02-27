package demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.sun.research.ws.wadl.Request;

import demo.common.jedis.JedisManager;
import demo.common.jedis.ShardedJedisProvider;
import demo.common.utils.GsonUtils;
import demo.service.ITestService;


@Controller
public class testController {

	@Autowired
	private ITestService testService;
	
	@RequestMapping("test")
	public String test(HttpServletRequest request,HttpServletResponse response){
		List<Map<String,Object>> map = testService.test();
		/*request.setAttribute("users",GsonUtils.listToJson(map));*/
		ShardedJedisProvider jedis = JedisManager.getShardedJedis("redis");
		request.setAttribute("users",jedis.get("mylove"));
		jedis.close();
		return "test/index";
	}
}
