package cn.org.j2ee.excel.common;

import java.util.Random;

public abstract class RandomUtil {

	private static String strs = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM!@#$%^&*()";

	public static String randomStr(Integer length) {
		String string = "";
		if(length > 0) {
			for (int i = 0; i < length; i++) {
				Random rand = new Random();
				int len = rand.nextInt(strs.length());
				string += strs.charAt(len);
			}
		}
		
		return string;
	}
	
	private static String cnStrs = "汉字大全,基于国务院公布的《通用规范汉字表》，将8105个字分为三级，其中一级字表为常用字级，收字3500字 ";

	public static String randomCnStr(Integer length) {
		String string = "";
		if(length > 0) {
			for (int i = 0; i < length; i++) {
				Random rand = new Random();
				int len = rand.nextInt(cnStrs.length());
				string += cnStrs.charAt(len);
			}
		}
		
		return string;
	}
	
	public static void main(String[] args) {
		System.out.println(randomCnStr(5));
	}
}
