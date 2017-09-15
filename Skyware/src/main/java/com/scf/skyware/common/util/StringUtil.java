package com.scf.skyware.common.util;

public class StringUtil {
	public static String ToBR(String data){
		if(data == null || data == ""){
			return null;
		}
		else{
			data = data.replaceAll("\r\n", "<br>");
			return data;
		}
	}
	
	public static String brTo(String data){
		if(data == null || data == ""){
			return null;
		}
		else{
			data = data.replaceAll("<br>", "\r\n");
			return data;
		}
	}

}
