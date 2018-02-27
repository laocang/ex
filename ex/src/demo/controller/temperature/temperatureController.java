package demo.controller.temperature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("temperature")
@Controller
public class temperatureController {
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "temperature/index";
	}

}
