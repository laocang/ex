package lc.dinner.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import lc.dinner.dao.IDinnerDao;
import lc.dinner.model.foodCategoryModel;
import lc.dinner.model.userModel;
import lc.dinner.service.IDinnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("dinner")
@Controller
public class eatController {
		
	@Autowired
	private IDinnerService dinnerServiceImpl;
	@Autowired
	private IDinnerDao dinnerDao;
	
	@RequestMapping("index")
	public String eatIndex(HttpServletRequest request){
		List<userModel> users = dinnerDao.getUser();
		/*try{
			File f = new File("D:/category.txt");
			  if(!f.exists()){
				  f.createNewFile();
			  }
			  InputStreamReader is = null; 
			  is = new InputStreamReader(new FileInputStream(f));
			  BufferedReader bufferedReader = new BufferedReader(is);
			  String next = "";
			  String s = null;
			  List list= new ArrayList();
			  while((s=bufferedReader.readLine())!=null){
				  next+=s;
			  }
			  String[] s1 = next.split(",");
			  for(int i=0;i<s1.length;i++){
				  list.add(s1[i]);
			  }
			  request.setAttribute("category",list);
			  is.close();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		return "dinner/index";
	}
	
	@ResponseBody
	@RequestMapping("randomToEat")
	public String randomToEat(){
		int x = dinnerServiceImpl.getCount();
		Random rm = new Random();
		int y = rm.nextInt(x);
		List<foodCategoryModel> fcm = dinnerServiceImpl.getFood();
		foodCategoryModel f = fcm.get(y);
		return f.getName();
	}
	
	@RequestMapping("index2")
	public String index2(){
		
		return "dinner/index2";
	}
}
