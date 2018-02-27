package demo.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobUtils {

    /**   
     * WORD转HTML   
     * @param docfile WORD文件全路径   
     * @param htmlfile 转换后HTML存放路径   
     */    
    public static void wordToHtml(String docfile, String htmlfile)     
    {     
    	 System.out.println("word转成html开始");
        ActiveXComponent app = new ActiveXComponent("Word.Application");   // 启动word应用程序(Microsoft Office Word)  
        try    
        {     
            app.setProperty("Visible", new Variant(false));   // 设置word应用程序不可见     
            Dispatch docs = app.getProperty("Documents").toDispatch();// documents表示word程序的所有文档窗口，（word是多文档应用程序）      
            Dispatch doc = Dispatch.invoke(     
                    docs,     
                    "Open",     
                    Dispatch.Method,     
                    new Object[] { docfile, new Variant(false),   
                            new Variant(true) }, new int[1]).toDispatch();// 打开要转换的word文件    
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {     
                    htmlfile, new Variant(8) }, new int[1]); // 作为html格式保存到临时文件      
            Dispatch.call(doc, "Close", new Variant(false)); // 关闭word文件  
        }     
        catch (Exception e)     
        {     
        	System.out.println("word转成html失败");
            e.printStackTrace();     
        }     
        finally    
        {     
            app.invoke("Quit", new Variant[] {});//关闭word应用程序       
        }   
        System.out.println("word转成html结束");
    }
    
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }  
    
    public static String getHtmlContent(String htmlFile) throws IOException {
    	FileInputStream inputStream = new FileInputStream(htmlFile);
        byte[] getData = readInputStream(inputStream);
        String data = new String(getData, "gb2312");  
        return data;  
	}
    
    /**
     * html转word
     * @param htmlFile
     * @param docFile
     */
    public static void htmlToWord(String htmlFile, String docFile) {    
    	System.out.println("html转成word结束");
        ActiveXComponent app = new ActiveXComponent("Word.Application");     
        try {    
            app.setProperty("Visible", new Variant(false));    
            Dispatch docs = app.getProperty("Documents").toDispatch();    
            Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[] { htmlFile, new Variant(false), new Variant(true) }, new int[1]).toDispatch();    
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { docFile, new Variant(12) }, new int[1]);    
            Variant f = new Variant(false);    
            Dispatch.call(doc, "Close", f);    
        } catch (Exception e) {  
        	System.out.println("html转成word结束");
            e.printStackTrace();    
        } finally {    
            app.invoke("Quit", new Variant[] {});    
            ComThread.Release();    
        }    
        System.out.println("html转成word结束");
    } 
    
}
