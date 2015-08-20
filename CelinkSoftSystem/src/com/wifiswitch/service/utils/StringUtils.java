package com.wifiswitch.service.utils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * Created by lifaqiu on 14-4-30.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}
	
    public static String deNull(Object obj){
        if (obj == null || !(obj instanceof String)) {
            return "";
        }
        return String.valueOf(obj);
    }
    
    public static String getFirstImage(String images) {
		if(images != null){
			String[] str = images.split("\"");
			return str[3];
		}
		return null;
	}
    public static String[] getImages(String images) {
		if(images != null){
			String[] str = images.split("\"");
			return str;
		}
		return null;
	}
    

	/**
	 * 获取uuid
	 * 
	 * @return
	 */
	public static String getuuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
    
}
