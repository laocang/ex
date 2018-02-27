package demo.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

public class HtmlHelperUtils {
	 private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签   
	 public static String filterHtml(String str) {   
	        Pattern pattern = Pattern.compile(regxpForHtml);   
	        Matcher matcher = pattern.matcher(str);   
	        StringBuffer sb = new StringBuffer();   
	        boolean result1 = matcher.find();   
	        while (result1) {   
	            matcher.appendReplacement(sb, "");   
	            result1 = matcher.find();   
	        }   
	        matcher.appendTail(sb);   
	        return sb.toString();   
	    }   
	 
	public static String removeTag(String htmlStr) {
		  String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // script
		  String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // style
		  String regEx_html = "<[^>]+>"; // HTML tag
		  String regEx_space = "\\s+|\t|\r|\n";// other characters

		  Pattern p_script = Pattern.compile(regEx_script,
		    Pattern.CASE_INSENSITIVE);
		  Matcher m_script = p_script.matcher(htmlStr);
		  htmlStr = m_script.replaceAll("");
		  Pattern p_style = Pattern
		    .compile(regEx_style, Pattern.CASE_INSENSITIVE);
		  Matcher m_style = p_style.matcher(htmlStr);
		  htmlStr = m_style.replaceAll("");
		  Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		  Matcher m_html = p_html.matcher(htmlStr);
		  htmlStr = m_html.replaceAll("");
		  Pattern p_space = Pattern
		    .compile(regEx_space, Pattern.CASE_INSENSITIVE);
		  Matcher m_space = p_space.matcher(htmlStr);
		  htmlStr = m_space.replaceAll(" ");
		  return htmlStr;
		    }
	
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
      
    
    public static String delHTMLTagStr(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串 
    } 
    
    /** 
     * @param htmlStr 
     * @return 
     *  删除Html标签 
     */  
    public static String delHTMLTag(String htmlStr) {  
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        return htmlStr.trim(); // 返回文本字符串  
    }  
      
    public static String getTextFromHtml(String htmlStr){  
        htmlStr = delHTMLTag(htmlStr);  
        htmlStr = htmlStr.replaceAll("&nbsp;", "");  
        htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);  
        return htmlStr;  
    } 
	
	
	 /** 
	  * 正则表达式的切割 
      */  
    public static void splitShow(String str,String regex)  
    {  
        String result[] = str.split(regex);  
        for(String s:result){  
            System.out.println(s);
        }
    }  
    
    public static String stripHtml(String content) { 
    	// <p>段落替换为换行 
    	content = content.replaceAll("<p .*?>", "\r\n"); 
    	// <br><br/>替换为换行 
    	content = content.replaceAll("<br\\s*/?>", "\r\n"); 
    	// 去掉其它的<>之间的东西 
    	content = content.replaceAll("\\<.*?>", ""); 
    	// 还原HTML 
    	// content = HTMLDecoder.decode(content); 
    	return content; 
    }
    
    public static String clear(String htmlStr) {  
//    	String regEx ="<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
//
//    	Pattern p = Pattern.compile(regEx);
//    	Matcher m = p.matcher(htmlStr.toLowerCase());
//    	String result = htmlStr;
//    	if (m.find()) {
//    		result = m.replaceAll("");
//    	}
    	
    	String result = htmlStr.replaceAll("style\\s*=\\s*('[^']*'|\"[^\"]*\")" ,"");
        
//        String regex = "font.*px;";
//        String okContent =  htmlStr.replaceAll(regex, "");
        return result;
    }  
    
    public static String clearStyle(String htmlStr){
       return htmlStr.replaceAll("<script.*</script>", "")
        		.replaceAll("<style.*</style>", "")
        		.replaceAll("'", "''").
        		replaceAll("<(/?p|br[^>]*)>", "[--$1--]")
        		.replaceAll("<[^<>]+>", " ")
        		.replaceAll("\\[--([^-]+)--\\]", "<$1>");  
    }
    
    public static String clearWordFormat(String content) {  
        //把<P></P>转换成</div></div>保留样式  
        //content = content.replaceAll("(<P)([^>]*>.*?)(<\\/P>)", "<div$2</div>");  
        //把<P></P>转换成</div></div>并删除样式  
        content = content.replaceAll("(<P)([^>]*)(>.*?)(<\\/P>)", "<p$3</p>");  
        //删除不需要的标签  
        content = content.replaceAll("<[/]?(font|FONT|span|SPAN|xml|XML|del|DEL|ins|INS|meta|META|[ovwxpOVWXP]:\\w+)[^>]*?>", "");  
        //删除不需要的属性  
        content = content.replaceAll("<([^>]*)(?:lang|LANG|class|CLASS|style|STYLE|size|SIZE|face|FACE|[ovwxpOVWXP]:\\w+)=(?:'[^']*'|\"\"[^\"\"]*\"\"|[^>]+)([^>]*)>", "<$1$2>");  
        //删除<STYLE TYPE="text/css"></STYLE>及之间的内容  
//        int styleBegin = content.indexOf("<STYLE");  
//        int styleEnd = content.indexOf("</STYLE>") + 8;  
//        String style = content.substring(styleBegin, styleEnd);  
//        content = content.replace(style, "");  
        return content;  
    }  
    
      
    /**
     * 正则表达式的替换 
     *
     */  
    public static String replaceAllShow(String htmlStr,String regex,String newstr)  
    {  
    	Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(htmlStr);
        htmlStr = matcher.replaceAll(newstr);
//        System.out.println("替换后的值："+htmlStr);  
        return htmlStr;
    }  
	/**
	 * 去除word格式的字符串内容
	 * @param Htmlstring
	 * @return
	 */
	 public static String RemoveFont(String Htmlstring)
     {
		 Htmlstring = replaceAllShow(Htmlstring,"<font[\\s\\S]*?>", "");
		 Htmlstring = replaceAllShow(Htmlstring,"</font>", "");
		 Htmlstring = replaceAllShow(Htmlstring,"<a[^>]*>", "");
		 Htmlstring = replaceAllShow(Htmlstring,"<\\/a>", "");
		 Htmlstring = replaceAllShow(Htmlstring,"<!--[^>]*-->", "");
		 Htmlstring = replaceAllShow(Htmlstring,"style=.+?['|\"]", "");
		 Htmlstring = replaceAllShow(Htmlstring,"class=.+?['|\"]", "");
		 Htmlstring = replaceAllShow(Htmlstring,"id=.+?['|\"]", "");
		 Htmlstring = replaceAllShow(Htmlstring,"lang=.+?['|\"]", "");
		 Htmlstring = replaceAllShow(Htmlstring,"width=.+?['|\"]", "");
		 Htmlstring = replaceAllShow(Htmlstring,"height=.+?['|\"]", "");
		 Htmlstring = replaceAllShow(Htmlstring,"border=.+?['|\"]", "");
		 Htmlstring = replaceAllShow(Htmlstring,"face=.+?['|\"]", "");
		 Htmlstring = replaceAllShow(Htmlstring,"face=.+?['|\"]", "");
         return Htmlstring;
     }

     //去除HTML
     public static String RemoveHTML(String Htmlstring)
     {        
         String firstP="&lt;p&gt;&amp;nbsp;&lt;/p&gt;";  //去除第一行的换行标签 ，html为<p>&nbsp;</p>
         int inde= Htmlstring.indexOf(firstP);
         if(inde==0)
         {
             Htmlstring = Htmlstring.substring(firstP.length(), Htmlstring.length() - firstP.length());
         }

         //删除脚本   
         Htmlstring = replaceAllShow(Htmlstring,"<script[^>]*?>.*?</script>", "");
         Htmlstring = replaceAllShow(Htmlstring,"([\\r\\n])[\\s]+", "<br />");
         Htmlstring = replaceAllShow(Htmlstring,"-->", "");
         Htmlstring = replaceAllShow(Htmlstring,"<!--.*", "");
         Htmlstring = replaceAllShow(Htmlstring,"&(quot|#34);", "\"");
         Htmlstring = replaceAllShow(Htmlstring,"&(amp|#38);", "&");
         Htmlstring = replaceAllShow(Htmlstring,"&(lt|#60);", "<");
         Htmlstring = replaceAllShow(Htmlstring,"&(gt|#62);", ">");
         Htmlstring = replaceAllShow(Htmlstring,"&(font);", "");
         Htmlstring = replaceAllShow(Htmlstring,"&(iexcl|#161);", "\\xa1");
         Htmlstring = replaceAllShow(Htmlstring,"&(cent|#162);", "\\xa2");
         Htmlstring = replaceAllShow(Htmlstring,"&(pound|#163);", "\\xa3");
         Htmlstring = replaceAllShow(Htmlstring,"&(copy|#169);", "\\xa9");
         Htmlstring = replaceAllShow(Htmlstring,"&#(\\d+);", "");
         Htmlstring = Htmlstring.replace("<", "")
         .replace(">", "")
         .replace("\r\n", "")
         .replace("&nbsp;", "");
         Htmlstring = StringEscapeUtils.escapeHtml(Htmlstring).trim();
         return Htmlstring;
     }

}
