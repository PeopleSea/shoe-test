package shoes.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GenerateShoeNumberUtils {

	public static String genNumber() {
		Calendar cal = Calendar.getInstance();
		Date nowDate = cal.getTime();
		int num = nowDate.getDate(); 
		int flag = num%2;
		int length = 7;		
		if(flag == 0)
			length = 8;
		return randomNumer(length);
	}
	
	private static String randomNumer(int length) {
		StringBuilder sb = new StringBuilder();
		Random ran = new Random();
		for(int i = length;i >0;i--) {
			int no = 0;
			if(i>4) {
				no = ran.nextInt(9);
				sb.append(String.valueOf(no));
			} else {
				no = ran.nextInt(26) + 1;
				sb.append((char) (no + 64));
			}			
		}
		return sb.toString();
	}

}
