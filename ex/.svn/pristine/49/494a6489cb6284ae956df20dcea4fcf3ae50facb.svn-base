package lc.watchshop.service.impl;

import java.util.HashMap;
import java.util.Map;

import lc.dinner.dao.IDinnerDao;
import lc.watchshop.dao.IOrderDao;
import lc.watchshop.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderServiceImpl")
public class orderServiceImpl implements IOrderService{
	
	@Autowired
	private IOrderDao orderDao;

	public String addOrder(String product, String name, String tel,
			String city, String area, String addr, String price, String payway,
			String privince,String beizhu) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("product", product);
		map.put("name", name);
		map.put("tel", tel);
		map.put("city", city);
		map.put("area", area);
		map.put("addr", addr);
		map.put("price", price);
		map.put("payway", payway);
		map.put("privince", privince);
		map.put("beizhu", beizhu);
		String result = orderDao.addOrder(map);
		return result;
	}
	

	
}
