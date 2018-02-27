package demo.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MACAddressUtils {
	public MACAddressUtils() {
	}

	/***
	 * 获取Mac地址值
	 * 
	 * @return
	 */
	public static String getMACAddress() {
		String address = "";
		String os = System.getProperty("os.name");
		if (os != null && os.startsWith("Windows")) {
			try {
				String command = "cmd.exe /c ipconfig /all";
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						p.getInputStream(), "UTF-8"));
				String line = "";
				while ((line = br.readLine()) != null) {
//					System.out.println("这里获取的值："
//							+ new String(line.getBytes("ISO-8859-1"), "UTF-8"));
					if (line.indexOf("Physical Address") > 0) {
						int index = line.indexOf(":");
						index += 2;
						address = line.substring(index);
						break;
					}
				}
				br.close();
				return address.trim();
			} catch (IOException e) {
			}
		}
		return address;
	}
	/**
	 * 获取CPU序列号
	 * cmd命令行中 输入   ： wmic CPU get ProcessorID  可以查看CPU序列号
	 * @return
	 */
	public static String getCPUSerial() {
		
		Process process =null;
		try {
			String osname = System.getProperties().getProperty("os.name");
			if(osname.indexOf("dow")!=-1){//判断如果是window执行window平台下的命令获取cpuid
				process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
				process.getOutputStream().close();
				  Scanner sc = new Scanner(process.getInputStream());
				  sc.next();
				  String serial = sc.next();
				  return serial;
			}
			if(osname.indexOf("nux")!=-1){//判断如果是linux执行linux平台下的命令获取uuid
				process = Runtime.getRuntime().exec(new String[] { "blkid","/dev/sda1"});
				 process.getOutputStream().close();
				  Scanner sc = new Scanner(process.getInputStream());
				  String s = "";
				  while(sc.hasNext()){
					  s+=sc.next();
				  }
				  int x = s.indexOf("=");
				  s= s.substring(x+2);
				  int y = s.indexOf("T");
				  s = s.substring(0, y-1);
				  String serial = s;
				  return serial;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "无CPU_ID被读取";		
		
/*		String result = "";
		try {
			File file = File.createTempFile("tmp", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);
			String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
					+ "Set colItems = objWMIService.ExecQuery _ \n"
					+ "   (\"Select * from Win32_Processor\") \n"
					+ "For Each objItem in colItems \n"
					+ "    Wscript.Echo objItem.ProcessorId \n"
					+ "    exit for  ' do the first cpu only! \n" + "Next \n";

			// + "    exit for  \r\n" + "Next";
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec(
					"cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			 System.out.println(System.getProperties().getProperty("os.name"));
			input.close();
			file.delete();
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		if (result.trim().length() < 1 || result == null) {
			result = "无CPU_ID被读取";
		}
		return result.trim();*/
	}
	
	/**
     * 获取主板序列号
     *
     * @return
     */
    public static String getMotherboardSN() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
 
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
 
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }
 
    /**
     * 获取硬盘序列号
     *
     * @param drive
     *            盘符
     * @return
     */
    public static String getHardDiskSN(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
 
            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }
	
	public static void main(String[] args) {
		System.out.println("服务端的cupId值:" + getCPUSerial());
		System.out.println("获取mac地址：" + getMACAddress());
		System.out.println("主板  SN:" + getMotherboardSN());
	    System.out.println("C盘   SN:" + getHardDiskSN("c"));
	}
}
