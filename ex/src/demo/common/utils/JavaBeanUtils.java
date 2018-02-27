package demo.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;


public class JavaBeanUtils {
	
	public static <T> List<Map<String, Object>> addPropertys( List<T> list,List<Map<String, Object>> mapList){
		if(list!=null && mapList!=null &&  list.size()>0 && mapList.size()>0){
			List<Map<String, Object>> list2=new ArrayList<Map<String,Object>>();
			for (int i = 0; i < list.size(); i++) {
				Object obj=list.get(i);
				Map<String, Object> map2 = addPropertys(obj, mapList.get(i));
				if(map2!=null){
					list2.add(map2);
				}
			}
			return list2;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> addPropertys(Object obj,Map<String, Object> map){
		Map<String, Object> map2=null;
			try {
				map2 = BeanUtils.describe(obj);
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					map2.put(entry.getKey(), entry.getValue());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		return map2;
	}
	/**
	 * 获取实体类的属性值的名称
	 * @param model
	 * @return
	 */
	public List<String> getModelProperty(Object model){
		List<String> properList = new ArrayList<String>(); 
		//获取实体类的所有属性，返回Field数组  
		Field[] field = model.getClass().getDeclaredFields();
        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
            String name = field[j].getName();    //获取属性的名字
            properList.add(name);
         }
        return properList;
	}
}
