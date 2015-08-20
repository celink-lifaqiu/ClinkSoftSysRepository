package com.wifiswitch.service.utils;

import org.springframework.util.StringUtils;

/**
* @ClassName: VersionCompare 
* @Description: TODO(版本比较) 
* @author Famor  
* @date 2014-4-21 下午2:20:32 
 */
public class VersionCompare {
	public static void main(String[] args) {
		String[] versions = new String[]{"17.2.3", "17.2.3", "17.2.4.013", "17.20.4.00", "17.20.4.001"};
		String maxOne = "0";
		int flag = 0;
		
		for(int i=0; i<versions.length; i++){
			flag = compare(maxOne, versions[i]);
			
			if(flag == -1){
				maxOne = versions[i];
			}
		}
		System.out.println("maxOne: " + maxOne);
	}


	/**
	 * 比较两个版本号之间的大小
	 * @param version1	版本号1
	 * @param version2	版本号2
	 * @return int		如果version1 > version2, 返回 1;如果version1 = version2, 返回0;如果version1 < version2,返回 -1
	 */
	public static int compare(String version1, String version2) {
		if(StringUtils.isEmpty(version1) || StringUtils.isEmpty(version2))
			throw new IllegalArgumentException("版本号不能为空");
		
		int index1 = 0;
		int index2 = 0;
		while(index1 < version1.length() && index2 < version2.length()){
			int[] number1 = getValue(version1, index1);
			int[] number2 = getValue(version2, index2);
			
			if(number1[0] < number2[0]){
				return -1;
			}else if (number1[0] > number2[0]){
				return 1;
			}else{
				index1 = number1[1] + 1;
				index2 = number2[1] + 1;
			}			
		}
		if(index1 == version1.length() && index2 == version2.length()){ 
			return 0;
		}
		if(index1 < version1.length()){
			return 1;
		}else{
			return -1;
		}
	}
	
	/**
	 * 将两个.之间的数字转化为一个整数
	 * @param version	版本号
	 * @param index		开始位置
	 * @return int[]	参数1：两个.之间的数字；参数2：.所在的数组索引
	 */
	public static int[] getValue(String version, int index) {
		int[] value_index = new int[2];
		StringBuffer sb = new StringBuffer();
		while(index < version.length() && version.charAt(index) != '.') {
			sb.append(version.charAt(index));
			index++;
		}
		value_index[0] = Integer.parseInt(sb.toString());
		value_index[1] = index;
		return value_index;
	}
}
