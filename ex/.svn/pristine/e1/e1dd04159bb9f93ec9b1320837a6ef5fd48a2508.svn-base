package demo.common.utils;

public class CheckUtils {
	 static String m_encryptKey = "telewate";
     public CheckUtils()
     { 
     
     }

     public static String GetDesSN(String key, String productid)
     {
         String mkey = DESDecrypt(key);
         return MD5Security.getMD5(DESUtility.encode(productid, mkey).getBytes());
     }

     //默认米钥向量
     private static byte[] Keys = { (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };

     /**
      * DES编码
      * @param encryptString 要编码的字符串
      * @return
      */
     private static String DESEncrypt(String encryptString)
     {
         return DESUtility.encode(encryptString, m_encryptKey);
     }
     
     /**
      * DES解码
      * @param decryptString 要解码的字符串
      * @return
      */
     private static String DESDecrypt(String decryptString)
     {
         return  DESUtility.decode(decryptString, m_encryptKey);
     }
}
