package demo.common.utils;

import java.util.UUID;

public class Contants {
	public Contants() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String OBJECT_EXSIT = "1";
	public static final String OBJECT_NOT_EXSIT = "-1";
	
	public static final String RESULTVAL = "val"; //返回结果的值
	public static final String SUCCESS = "1"; //1 表示登陆成功
	public static final String ERROR = "-1"; //-1表示出现错误
	
	public static final String ROLEID_ERROR= "角色名称不正确";
	public static final String USERNAME_ERROR = "用户名不存在!"; 
	public static final String PASSWORD_ERROR = "用户密码错误!";
	public static final String OLDPWD_SUCCESS = "用户密码正确!";
	public static final String OLDPWD_ERROR = "用户密码错误!";
	public static final String NOT_ACTIVE = "该租户未激活!";
	public static final String LOGIN_EXPIRED = "该租户已经过期!";
	
	
	public static final String PROPERFILE = "jdbc.properties";
	
	public static final String GETNEWSTIME = "2014-09-01";
	
	
	/**
	 * 电子政务V7.5平台配置常量
	 */
	/**
	 * 数据请求返回码
	 */
	public static final int RESCODE_SUCCESS = 1000;				//成功
	public static final int RESCODE_SUCCESS_MSG = 1001;			//成功(有返回信息)
	public static final int RESCODE_EXCEPTION = 1002;			//请求抛出异常
	public static final int RESCODE_NOLOGIN = 1003;				//未登陆状态
	public static final int RESCODE_NOEXIST = 1004;				//查询结果为空
	public static final int RESCODE_NOAUTH = 1005;				//无操作权限
	/**
	 * jwt
	 */
	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "TLW4BA5B5C85E86HH657WTAP";
	public static final int JWT_TTL = 60*60*1000;  //millisecond
	public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
	public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
	
	
	/**
	 * 获取唯一标识码的主键ID,统一转换为大写
	 * @return
	 */
	public static String getUUIDPK(){
		String result =   UUID.randomUUID().toString().trim().replaceAll("-", "");
//		result = result.toUpperCase();
		return result ;
	}
	
	public static void main(String[] args) {
		System.out.println(getUUIDPK().length());
	}
	
	/**
	 * 将经纬度坐标转换为WGS84的坐标点的计算方法进行转换
	 * @param value
	 * @return
	 */
	public static double getConversionValue(String value){
		double result = 0;
		if(value != null && !value.isEmpty()){
			String[] strs = value.split("°");
			String degree = null;
			if(strs.length >= 0){
				 degree = strs[0];
			}
			
			if(strs.length >= 1){
				String branch = strs[1].split("\\′")[0];
				String second = strs[1].split("\\′")[1];
				second = second.split("″")[0];
				 result = Double.parseDouble(degree)+Double.parseDouble(branch)/60+Double.parseDouble(second)/3600;
			}
		}
//		System.out.println("需要切割值："+value+"度："+degree+"分："+branch+"秒："+second);
		return result;
	}
	
	
	/**
	 * 移动一张图对应的标识符
	 */
	public static final String STATUS_N = "N"; //数据同步新任务需要交互
	public static final String STATUS_E = "E"; //数据同步表示更新或已经成功
	public static final String STATUS_Y = "Y"; //数据同步导出数据
	public static final String STATUS_D = "D"; //数据同步表示数据删除
	public static final String TABLE_ANALYSISNAME = "PDA_ONEMAP_ANALYSHOWRESULT"; //分析名称表名称
	public static final String TABLE_PKIDNAME = "OBJECTID"; //分析配置表的主键ID名称
	public static final String TABLE_SUDOCONFIG = "PDA_ONEMAP_SUDOCONFIG"; //移动公文包父类表名称
	public static final String TABLE_PKIDSUDOCONFIG = "OBJECTID"; //移动公文包父类表的主键ID名称
	public static final String TABLE_SUBCONFIG = "PDA_ONEMAP_SUBCONFIG"; //移动公文包子类表名称
	public static final String TABLE_PKIDSUBCONFIG = "OBJECTID"; //移动公文包子类表的主键ID名称
	
	
	public static final String TABLE_REGION = "PDA_ONEMAP_REGION_CONFIG"; //快速定位表名称
	public static final String TABLE_PKIDREGION = "OBJECTID";//快速定位表主键ID名称
	
	public static final String TABLE_PROJECT = "PDA_ONEMAP_PROJECT_NAMESHOW";//项目地块分析表名称
	public static final String TABLE_PKIDPROJECT = "OBJECTID";//项目地块分析表主键ID名称
	
	public static final String TABLE_PROJECTPLOTSQUERY = "PDA_ONEMAP_PROJECT_PLOTSQUERY";//项目地块查询表名称
	public static final String TABLE_PKIDPROJECTPLOTSQUERY = "ID";//项目地块查询表主键ID名称
	public static final String TABLE_SCALE = "PDA_ONEMAP_SCALE_DATA";//比例尺表名称
	public static final String TABLE_PKIDSCALE = "OBJECTID";//比例尺表主键ID名称
	
	public static final String TABLE_ENVELOPE = "PDA_ONEMAP_SHOWENVELOPE";//显示范围表名称
	public static final String TABLE_PKIDENVELOPE = "OBJECTID";//显示范围表主键ID名称
	
	public static final String TABLE_SKETCHMAP = "PDA_ONEMAP_SKETCHMAP";//示意图表名称
	public static final String TABLE_PKIDSKETCHMAP = "OBJECTID";//示意图表主键ID名称

	public static final String TABLE_FEATURESET = "PDA_ONEMAP_FEATURELAYERSET"; //移动政务跳转配置属性分析
	public static final String TABLE_PKIDFEATURESET = "OBJECTID"; //移动政务跳转配置属性分析的主键ID名称
	
}
