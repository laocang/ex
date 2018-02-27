package demo.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 16位 加密算法
 * 
 * @author Administrator
 * 
 */
public class MD5Security {
	
	/**
	 * getMD5 将字节转换为MD5编码过的字符串
	 * 
	 * @param source
	 *            源字节数组
	 * @return
	 * @return String
	 */
	public static String getMD5(byte[] source) {
		String s = "";

		// 用来将字节转换成 16 进制表示的字符 
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			// 表示转换结果中对应的字符位置   
			int k = 0;
			for (int i = 0; i < 16; i++) {
				// 从第一个字节开始，对 MD5的每一个字节转换成 16进制字符的转换    
				byte byte0 = tmp[i];
				// 取字节中高 4位的数字转换
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				// 取字节中低 4 位的数字转换
				str[k++] = hexDigits[byte0 & 0xf];
			}
			// 提取md5(x,16),并转化为大写
			s = new String(str).substring(8, 24).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String md5_16(String input) throws Exception {
		return code(input, 16);
	}
	
	/**
	 * 这里进行MD5的加密算法
	 * @param sourceStr
	 * @param digit
	 * @return
	 */
	public static String Md5(String sourceStr, int digit) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			result = buf.toString(); // 这里是 32位 加密
			if (digit == 16) {
				result = buf.toString().substring(8, 24); // 这里是 16位 加密
			}
			// System.out.println("result: " + result);//32位的加密
			// System.out.println("result: " +
			// buf.toString().substring(8,24));//16位的加密
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		return result.toUpperCase();
	}

	private static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		int t;
		for (int i = 0; i < 16; i++) {
			t = bytes[i];
			if (t < 0)
				t += 256;
			sb.append(hexDigits[(t >>> 4)]);
			sb.append(hexDigits[(t % 16)]);
		}
		return sb.toString();
	}

	public static String code(String input, int bit) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance(System.getProperty(
					"MD5.algorithm", "MD5"));
			if (bit == 16)
				return bytesToHex(md.digest(input.getBytes("utf-8")))
						.substring(8, 24);
			return bytesToHex(md.digest(input.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Could not found MD5 algorithm.", e);
		}
	}

	public static String md5_3(String b) throws Exception {
		MessageDigest md = MessageDigest.getInstance(System.getProperty(
				"MD5.algorithm", "MD5"));
		byte[] a = md.digest(b.getBytes());
		a = md.digest(a);
		a = md.digest(a);

		return bytesToHex(a);
	}

	/**
	 * 这里进行获取 序列号
	 * 
	 * @return
	 */
	public static String createSN() {
		String sn = null;
		int temp = (int) (Math.random() * 100); //这里进行产生0-100的随机数
		try {
			sn = getMD5((temp+"").getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sn;
	}
}
