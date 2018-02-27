package demo.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class HttpPostUtils {
	
	/**
	 * 独立模式账号批量导入接口  post方法请求
	 * @param url  URL 不带问号
	 * @param params  普通参数 name1=value1&name2=value2 
	 */
    public static String sendBatch(String url, String params) {
        String strResult = "";
        String smsUrl = url+"?"+params;
        
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(smsUrl);
        
        //传JSON body
        JSONObject jsonData = new JSONObject();
        ArrayList<String> jsonList = new ArrayList<String>();  //JSON带数组
        jsonList.add("chensp1111");
        jsonList.add("chensp2222");
        jsonData.put("Accounts", jsonList);
        try {

            httppost.addHeader("Content-type","application/json");
            httppost.setEntity(new StringEntity(jsonData.toString(),"utf-8"));
            
            //返回值
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String conResult = EntityUtils.toString(response.getEntity());
                JSONObject sobj = new JSONObject();
                sobj = JSONObject.fromObject(conResult);
                System.out.println("sobj："+sobj);
                String ActionStatus = sobj.getString("ActionStatus");
                if (ActionStatus.equals("OK")) {
                    String FailAccounts = sobj.getString("FailAccounts");//
                    strResult += "访问成功";
                    System.out.println("未被保存的用户：" + FailAccounts);
                } else {
                    strResult += "访问失败";
                }

            } else {
                String err = response.getStatusLine().getStatusCode() + "";
                strResult += "访问失败222:" + err;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("结果："+strResult);
        return strResult;
    }
    
    /**
     * 独立模式账号个别导入接口  post方法请求
	 * @param url  URL 不带问号
	 * @param params  普通参数 name1=value1&name2=value2 
     */
    public static JSONObject sendSingle(String url, String params,String Identifier,String Nick,String tenantId) {
//        String strResult = "";
        String smsUrl=url+"?"+params;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(smsUrl);
        //传JSON body
        JSONObject jsonData = new JSONObject();
        jsonData.put("Identifier",Identifier);
//        jsonData.put("FaceUrl", "http://www.qq.com");
        jsonData.put("Nick",Nick);

        JSONObject sobj = new JSONObject();
        try {
            httppost.addHeader("Content-type","application/json");
            httppost.setEntity(new StringEntity(jsonData.toString(),"utf-8"));

            //返回值
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String conResult = EntityUtils.toString(response.getEntity());
                //JSONObject sobj = new JSONObject();
                sobj = JSONObject.fromObject(conResult);
//    			LogUtils.event("==============>>>>>>云信通："+sobj,tenantId);
                //System.out.println("sobj："+sobj);
//                String ActionStatus = sobj.getString("ActionStatus");
//                strResult = ActionStatus;

            } else {
            	sobj = null;
//                String err = response.getStatusLine().getStatusCode() + "";
//                strResult += "访问失败:" + err;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sobj;
    }
    /**
	 * 获取IM sig
	 * 
	 * @param tenantId
	 * @param mobileType
	 */
	public static String getIMSig(String sdkAppid,
			String identifier,String tenantId) {

		String result = "";
		tls_sigcheck demo = new tls_sigcheck();

		// 使用前请修改动态库的加载路径
		String dllFlie;

		StringBuilder strBuilder = new StringBuilder();
		String s = "";

		BufferedReader br;
		try {

			dllFlie = PathUtils.getWebAppsPath() + "\\pzFile\\";

		    //判断文件夹是否存在,如果不存在则创建文件夹
			File file = new File(dllFlie);
			if (!file.exists()) {
				 //System.out.println("===========");
				 result="0";
			}else{
				demo.loadJniLib(dllFlie + "jnisigcheck.dll");

				File priKeyFile = new File(dllFlie + "private_key");

				br = new BufferedReader(new FileReader(priKeyFile));
				while ((s = br.readLine()) != null) {
					strBuilder.append(s + '\n');
				}
				br.close();
				String priKey = strBuilder.toString();
				// System.out.println("priKey****" + priKey);

				int ret = demo.tls_gen_signature_ex2(sdkAppid, identifier, priKey);

				if (0 != ret) {
					// System.out.println("ret " + ret + " " + demo.getErrMsg());
					result = "error";
				} else {
					 //System.out.println("sig:\n" + demo.getSig());
					result = demo.getSig();
				}
				
/*
                //校验 
		        File pubKeyFile = new File("E:\\peizhiFile\\public_key");
		        br = new BufferedReader(new FileReader(pubKeyFile));
				strBuilder.setLength(0);
		        while ((s = br.readLine()) != null) {
		            strBuilder.append(s + '\n');
		        }
		        br.close();
		        String pubKey = strBuilder.toString();        
				ret = demo.tls_check_signature_ex2(demo.getSig(), pubKey, "1400016936", "im47851007");
		        if (0 != ret) {
		            System.out.println("ret " + ret + " " + demo.getErrMsg());
		        }
		        else
		        {
		            System.out.println("--\nverify ok -- expire time " + demo.getExpireTime() + " -- init time " + demo.getInitTime());
		        }  */ 
			}
			
 
			// 校验
		} catch (FileNotFoundException e) {
			LogUtils.error(e,"======>>>云信通：usersig错误", tenantId);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LogUtils.error(e,"======>>>云信通：usersig错误", tenantId);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;// ;
	}

/*******************微信接口*****************************/
	//微信接口
		//上传多媒体文件，获取media_id(如：缩略图)
		public static final String MEDIA_UPLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload";
		//获取用户列表openID
		public static final String USER_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get";
		//客服群发文本
		public static final String MESSAGE_CUSTOM_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send"; 
		//微信群发
		public static final String MESSAGE_MSAA_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send";
		//上传图文素材，获取media_id
		public static final String MEDIA_UPLOADNEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews"; 
		//上传图文消息内的图片，获取url
		public static final String MEDIA_UPLOADIMG_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";
		
		
	/**
	 * 带JSON参数的链接(统一)
	 * @param url
	 * @param params
	 * @param jsonData
	 * @return
	 */
	public static JSONObject sendJSONMass(String url, String params,JSONObject jsonData) {
		// TODO Auto-generated catch block
		HttpClient httpclient = new DefaultHttpClient();
		System.out.println("params:"+params);
		HttpPost httppost = new HttpPost(url + "?" + params);  //链接
		JSONObject sobj = new JSONObject();
		try {

			httppost.addHeader("Content-type", "application/json");
			if(jsonData == null){
				
			}else{
				httppost.setEntity(new StringEntity(jsonData.toString(), "utf-8"));  //传JSON
			}
			// 返回值
			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				String conResult = EntityUtils.toString(response.getEntity());
				sobj = JSONObject.fromObject(conResult);
				System.out.println("sobj：" + sobj);
			} else {
				sobj =null;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sobj;
	}

	/**
	 * 微信上传多媒体接口简介
	 * @param url
	 * @param access_token
	 * @param type 类型:image,voice,video,thumb(缩略图)
	 * @param file
	 * @return
	 */
	public static JsonObject sendImageMass(String access_token,String type, File file) {
		// TODO Auto-generated catch block
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
		String uploadurl="";
		if(type.equals("1")){
			uploadurl = String.format("%s?access_token=%s", MEDIA_UPLOADIMG_URL,access_token);//url图片
		}else{
			uploadurl = String.format("%s?access_token=%s&type=%s", MEDIA_UPLOAD_URL,access_token, type);//缩略图
		}
		PostMethod post = new PostMethod(uploadurl);
		post.setRequestHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0");
		post.setRequestHeader("Host", "file.api.weixin.qq.com");
		post.setRequestHeader("Connection", "Keep-Alive");
		post.setRequestHeader("Cache-Control", "no-cache");
		  JsonObject json = new JsonObject();
		          try
		          {
		             if (file != null && file.exists())
		             {
		                 FilePart filepart = new FilePart("media", file, "image/jpeg",
		                         "UTF-8");
		                  Part[] parts = new Part[] { filepart };
		                  MultipartRequestEntity entity = new MultipartRequestEntity(
		                          parts,
		  
		                          post.getParams());
		                  post.setRequestEntity(entity);
		                  int status = client.executeMethod(post);
		                  if (status == HttpStatus.SC_OK){
		                	  
		                	  
		                      String responseContent = post.getResponseBodyAsString();
		                      JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
		                      json = jsonparer.parse(responseContent)
		                              .getAsJsonObject();
		                     System.out.println("json:"+json);
		                      if (json.get("errcode") == null)// {"errcode":40004,"errmsg":"invalid media type"}
		                      { 
		                    	  // 上传成功  {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
//		                          result = json.get("media_id").getAsString(); //缩略图
		                          
		                      }
		                  }
		              }
		          }
		          catch (Exception e)
		          {
		              e.printStackTrace();
		          }
		          return json;
	}


	/**
	 * 上传图文素材
	 * @param modelMap  
	 * title
	 * content
	 * @return
	 */
	public static JSONObject uploadwxData(String ACCESS_TOKEN,List<Map<String,Object>> modelMap){
		String params = "access_token="+ACCESS_TOKEN;
		JSONObject jsonData = new JSONObject();  //JSON构造
		try {
			if(modelMap != null && modelMap.size()>0){
				 //传JSON body
				ArrayList<String> articlesList = new ArrayList<String>(); //多个素材
				    for(int i=0;i<modelMap.size();i++){
				    	JSONObject articlesJson = new JSONObject();
			            articlesJson.put("title", modelMap.get(i).get("title"));
			            //  暂无图片
			            articlesJson.put("thumb_media_id", modelMap.get(i).get("thumb_media_id"));
			            articlesJson.put("author", "特力惠");
//			            articlesJson.put("digest", "");
			            articlesJson.put("show_cover_pic", 0);
			            //1.替换里面的图片
			            String contents = (String) modelMap.get(i).get("content");
			            //根据<img src=截一些出来
			    		String[] httpStr=contents.split("<img src=");
//			    		System.out.println("size:"+httpStr.length);
			    		if(httpStr.length>0){
			    			for(int j=1;j<httpStr.length;j++){
				    			String[] jpgStr = httpStr[j].split(".jpg");
				    			if(jpgStr.length > 0){
//				    				System.out.println("jpgStr[0]:"+jpgStr[0]+".jpg\"");
				    				String[] TlwAttachStr = jpgStr[0].split("TlwAttach");
				    				if(TlwAttachStr.length>1){
				    					String imgUrl = "/TlwAttach"+TlwAttachStr[1]+".jpg";
						    			//将
						    			JsonObject json = sendMediaUploadIMG(ACCESS_TOKEN,imgUrl);
										 if (json !=null && json.get("errcode") == null){ //成功
											String mediaImgUrl = json.get("url").getAsString(); //缩略图
							    			contents = contents.replaceAll(jpgStr[0]+".jpg\"", mediaImgUrl);
							             }else{
							    			contents = contents.replaceAll(jpgStr[0]+".jpg\"", "");
							             }
				    				}
				    			}
				    		}
			    		}
			            articlesJson.put("content", contents);//内容里面有图片
			            articlesJson.put("content_source_url", "");
			            articlesList.add(articlesJson.toString());//带json的  多条
				    }
				
		        jsonData.put("articles", articlesList);//带json的
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSONObject resultJson = HttpPostUtils.sendJSONMass(MEDIA_UPLOADNEWS_URL,params,jsonData);
		System.out.println("resultJson:"+resultJson);
		return resultJson;
	}
	/**
	 * 群发
	 * @param ACCESS_TOKEN
	 * @param media_id
	 * @param msgtype
	 * @return
	 */
	public static JSONObject sendwxmass(String ACCESS_TOKEN,String media_id,String msgtype){
		msgtype = "mpnews";
		String params = "access_token="+ACCESS_TOKEN;
		JSONObject jsonData = new JSONObject();  //JSON构造
		//JSONObject 参数
		List<String> wxUser= getWXUser(USER_GET_URL,ACCESS_TOKEN,"",1);
		System.out.println("wxUser:"+wxUser);
		try {
				 //传JSON body
		        jsonData.put("touser", wxUser); //带List<Map<String,Object>>的
		        jsonData.put("msgtype", msgtype); //只做图文群发
		        String temp="";
		        if(msgtype.equals("text")){
		        	temp = "content";
		        }else{
		        	temp = "media_id";
		        }
		        JSONObject textJson = new JSONObject();
		        textJson.put(temp, media_id);
		        jsonData.put(msgtype, textJson);//text或mpnews
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSONObject resultJson = HttpPostUtils.sendJSONMass(MESSAGE_MSAA_SEND_URL,params,jsonData);
		return resultJson;
	}

	
	/**
	 * 获取微信用户 openID
	 * @return
	 */
	static List<String> openidList = new ArrayList<String>();
	public static List<String> getWXUser(String url,String ACCESS_TOKEN,String NEXT_OPENID,int flag) {
		if(flag == 1){
			openidList = new ArrayList<String>();
		}
		String params = "access_token=" + ACCESS_TOKEN+"&next_openid="+NEXT_OPENID;
		JSONObject resultJson = HttpPostUtils.sendJSONMass(USER_GET_URL, params, null);
		String count = resultJson.getString("count");
    	
    	if(Integer.parseInt(count) != 0){  //说明还有用户没发

    		Map<?, ?> requestMap = (Map<?, ?>)resultJson; //JSON
        	Map<?, ?> msgMap = (Map<?, ?>)requestMap.get("data");
        	@SuppressWarnings("unchecked")
    		List<String> topicList= (List<String>) msgMap.get("openid");
        	openidList.addAll(topicList);
        	
			String next_openid = resultJson.getString("next_openid");
			getWXUser(USER_GET_URL,ACCESS_TOKEN,next_openid,2);//再调一次
		}
		return openidList;
	}

	/**
	 * 获取缩略图
	 * @return
	 * @throws URISyntaxException 
	 */
	public static JsonObject sendMediaUpload(String accessToken,String imgUrl) throws URISyntaxException{
		JsonObject result = null;
		if (accessToken != null)// token成功获取
		{
			File file = new File(imgUrl); // 获取本地文件
		    if (!file.exists()) {
				//不存在
				return result;
			}
		     JsonObject json = HttpPostUtils.sendImageMass(accessToken, "thumb",file);// 上传文件
		     result=json;
		    /* if (json.get("errcode") == null)// {"errcode":40004,"errmsg":"invalid media type"}
             { 
           	  // 上传成功  {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
		    	 result = json.get("thumb_media_id").getAsString(); //缩略图
                 
             }else{
            	 result=json;
             }
		     if (result != null)
		     System.out.println(result);*/
		}
		return result;
	}

	/**
	 * 获取内容里面的图片
	 */
	public static JsonObject sendMediaUploadIMG(String accessToken,String imgUrl){
		JsonObject result = null;
		String picRealPathDir = PathUtils.getWebAppsPath();
		System.out.println("picRealPathDir:"+picRealPathDir);
		if (accessToken != null)// token成功获取
		{
			File file = new File(picRealPathDir + imgUrl); // 获取本地文件
			if (!file.exists()) {
				//不存在
				return result;
			}
			JsonObject json = HttpPostUtils.sendImageMass(accessToken, "1",file);// 上传文件
			result = json;
			/*if (json.get("errcode") == null)// {"errcode":40004,"errmsg":"invalid media type"}
            { 
          	  // 上传成功 {"url":"http://mmbiz.qpic.cn/mmbiz_jpg/iaS9Z0F0DSp6Oh7Cq0KyicjeqduaRKhnbNia0eolWjiaS6Dv4pB6xxwwrx69yia4kt1TqdgFItN86iaYsQlHCqH1iatvA/0"}
		    	 result = json.get("url").getAsString(); //内容里面的图片
                
            }
		     if (result != null)
		     System.out.println(result);*/
		}
		return result;
	}
	
	
	 /**
     * 将接收的字符串转换成图片保存
     * @param imgStr 二进制流转换的字符串
     * @param imgPath 图片的保存路径
     * @param imgName 图片的名称
     * @return 
     *      1：保存正常
     *      0：保存失败
     */
    public static int saveToImgByStr(byte[] imgStr,String imgPath,String imgName){
        int stateInt = 1;
        if(imgStr != null && imgStr.length > 0){
            try {
                // 将字符串转换成二进制，用于显示图片  
                // 将上面生成的图片格式字符串 imgStr，还原成图片显示  
     
                InputStream in = new ByteArrayInputStream(imgStr); //读取数据
                
                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file); //
                   
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = in.read(b)) != -1) {
                    fos.write(b, 0, nRead); 
                }
                fos.flush();
                fos.close();
                in.close();
     
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }
    
	
	
    public static void main(String[] args) {
        //批量导入
//		String url="https://console.tim.qq.com/v4/im_open_login_svc/multiaccount_import";
//		String params="usersig=eJxlj01PgzAAhu-8CsIVY1pKgZl4ACQOxcRlNcNdSC0dNrC2gU4nxv-uxCWSeH6evB*flm3bDinWl5QxdZCmMh*aO-aV7QDn4g9qLeqKmgr19T-Ij1r0vKI7w-sJQoyxB8DcETWXRuzE2VCHcYCBD4No5gx1W01FvyH*KQEGCxTMFdFM8CF7SvNV2j-DvBvZbcKP91uSAdIsO1K00udhqRcq0cNrIcotU*g9b8qycx8JVfubOE9lMsY*2kSMvpA7d7X0kNvi0cD1JhvC*HpWacSenwdFAAHgh3hG33g-CCUnwQMQQ**k-Fy3vqxvm*pdqw__&identifier=ouzs164168&sdkappid=1400016936&apn=1&contenttype=json";

//      sendBatch(url,params);
		

        //单次导入
//    	String url="https://console.tim.qq.com/v4/im_open_login_svc/account_import";
//		String params="usersig=eJxlj01PgzAAhu-8CsIVY1pKgZl4ACQOxcRlNcNdSC0dNrC2gU4nxv-uxCWSeH6evB*flm3bDinWl5QxdZCmMh*aO-aV7QDn4g9qLeqKmgr19T-Ij1r0vKI7w-sJQoyxB8DcETWXRuzE2VCHcYCBD4No5gx1W01FvyH*KQEGCxTMFdFM8CF7SvNV2j-DvBvZbcKP91uSAdIsO1K00udhqRcq0cNrIcotU*g9b8qycx8JVfubOE9lMsY*2kSMvpA7d7X0kNvi0cD1JhvC*HpWacSenwdFAAHgh3hG33g-CCUnwQMQQ**k-Fy3vqxvm*pdqw__&identifier=ouzs164168&sdkappid=1400016936&apn=1&contenttype=json";
//
//        sendSingle(url,params);
    	
    	
//    	File file = new File("F:/load/图片素材/pugongying.jpg");
//    	sendUpload("283","Img",file);
    	
    }
 /**************************************************************************************************/   
    /**
     * 添加附件到附件服务器
	 * csp
	 * @param url
	 * @param access_token
	 * @param type 类型:image,voice,video,thumb(缩略图)
	 * @param file
	 * @return
	 */
	public static JSONObject sendUpload(String tenantId,String fileType, File file,String filePath) {
		// TODO Auto-generated catch block
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
		//附件服务器地址
		String uploadurl=String.format("http://10.0.7.56:8080/TlwAttach/upload/uploadAttact?tenantId="+tenantId+"&fileType="+fileType+"&filePath="+filePath);//
		
		PostMethod post = new PostMethod(uploadurl);
		post.setRequestHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0");

		post.setRequestHeader("Connection", "Keep-Alive");
		post.setRequestHeader("Cache-Control", "no-cache");
		JSONObject json = new JSONObject();
		          try
		          {
		             if (file != null && file.exists())
		             {
		                 FilePart filepart = new FilePart("media", file, "image/jpeg",
		                         "UTF-8");
		                  Part[] parts = new Part[] { filepart };
		                  MultipartRequestEntity entity = new MultipartRequestEntity(
		                          parts,
		  
		                          post.getParams());
		                  post.setRequestEntity(entity);
		                  int status = client.executeMethod(post);
		                  if (status == HttpStatus.SC_OK){
		                	  
		                      String responseContent = post.getResponseBodyAsString();
//		                      System.out.println("********"+responseContent+"*******");
		                      json = JSONObject.fromObject(responseContent); //将字符串{“id”：1}
//							  String path = jsStr.getString("path");//获取id的值
//							  System.out.println("=====>>>"+path);

		                  }else
		                	  json = null;
		              }
		          }
		          catch (Exception e)
		          {
		              e.printStackTrace();
		          }
		          return json;
	}
	
	 public static String removeUpload(String path) {
	        String strResult = "";
	        String url = String.format("http://10.0.7.56:8080/TlwAttach/upload/removeAttact?path="+path);//
	        
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(url);
	        
	        try {

	            httppost.addHeader("Content-type","application/json");
	            
	            //返回值
	            HttpResponse response = httpclient.execute(httppost);
	            if (response.getStatusLine().getStatusCode() == 200) {
	            	strResult = EntityUtils.toString(response.getEntity());
//	                System.out.println("*******"+strResult);
	            } else {
	                String err = response.getStatusLine().getStatusCode() + "";
	                strResult += "访问失败:" + err;
	            }
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return strResult;
	    }
}