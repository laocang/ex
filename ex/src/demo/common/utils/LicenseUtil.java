package demo.common.utils;
/**
 * 这里是进行验证硬件号和序列号的工具类
 * @author Administrator
 *
 */
public class LicenseUtil {
	private static boolean isEnable(String arg1, String arg2, String HID,
			String SN) {
		boolean result = false;
		String tempSN = "";
		try {
			tempSN = MD5Security.md5_16(DESUtility.encode(HID,
					DESUtility.decode(arg1, arg2)));
		} catch (Exception e) {
			return false;
		}
		if (tempSN.equals(SN)) {
			return true;
		}
		return false;
	}

	public static boolean validate(String HID, String SN) {
		boolean result = false;
		result = isEnable("OSpCHitJ9WVJZOlw71Vf2g==", "12345678", HID, SN);
		return result;
	}
	
	public static String createSN(String HID) {
		String tempSN = "";
		try {
			tempSN = MD5Security.md5_16(DESUtility.encode(HID,
					DESUtility.decode("OSpCHitJ9WVJZOlw71Vf2g==", "12345678")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempSN;
	}
	
	public static void main(String[] args) {
		String HID = "1B8242DB4797A0FF";
		String sn = createSN(HID);
		System.out.println("这里生成的序列号："+sn);
	}
}
