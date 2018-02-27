package demo.common.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PasswordUtils {
	public static String getPassword(int size){
		int ran = getRandom(1,size-2);
		String p1=getNumberPassword(ran);
		String p2=getletterPassword(size-ran);
		Set<Character> set=new HashSet<Character>();
		for (int i = 0; i < p1.length(); i++) {
			set.add(p1.charAt(i));
		}
		for (int i = 0; i < p2.length(); i++) {
			set.add(p2.charAt(i));
		}
		String result="";
		for (Character character : set) {
			result+=character.charValue();
		}
		if (result.length()!=size) {
			result += getletterPassword(size-set.size());
		}
		return result;
	}
	
	public static String getNumberPassword(int size){
		String password = "";
		for(int i = 1; i <= size; i++) {
            int ran = getRandom(48,57);
            char c = (char) ran;
            password+=c;
        }
		return password;
	}
	
	public static String getletterPassword(int size){
		String password = "";
		for(int i = 1; i <= size; i++) {
            int ran = getRandom(65,90);
            int ran1 = getRandom(97,122);
            int[] array={ran,ran1};
            int ran2=getRandom(0,1);
            char c = (char) array[ran2];
            password+=c;
        }
		return password;
	}
	
	public static int getRandom(int min,int max){
		if (min==0) {
			max = max+1;
		}
		 Random random = new Random();
	        int s = random.nextInt(max)%(max-min+1) + min;
	        return s;
	}
}
