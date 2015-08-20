package com.wifiswitch.service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: YinJianFeng
 * Date: 13-7-23
 * Time: 下午11:05
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtils {
    public static String aaData(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuffer result = new StringBuffer();
        try {
            String str = objectMapper.writeValueAsString(object);
            if (StringUtils.isEmpty(str)||"null".equalsIgnoreCase(str)) return "{ \"aaData\": []}";
            str = str.substring(1, str.length()-1);
            if (StringUtils.isEmpty(str)) return "{ \"aaData\": []}";
            String[] strArr = str.split("},");
            for (int i = 0; i < strArr.length; i++) {
                StringBuffer stringBuffer = new StringBuffer(strArr[i]);
                stringBuffer = stringBuffer.insert(1, "\"index\":" + (i+1) + ",");
                result.append(stringBuffer+"},");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "{ \"aaData\": ["+result.substring(0, result.length() - 2)+"]}";
    }

    public static String aaDataPagination(HttpServletRequest request, Collection<?> list,
                                          Integer totalDisplayRecords, Integer totalRecords) throws UnsupportedEncodingException {
        String sEcho = request.getParameter("sEcho");
        int echo = 0;
        if (sEcho != null) {
            echo = Integer.parseInt(sEcho);
        }
        JSONObject result = new JSONObject();
        String aa = aaData(list);
        JSONObject object = JSONObject.fromObject(aa);
        JSONArray array=JSONArray.fromObject(object.get("aaData"));
        result.put("sEcho", echo);
        result.put("iTotalRecords", totalRecords);
        result.put("iTotalDisplayRecords", totalDisplayRecords);
        result.put("aaData", array);
        return result.toString();
    }

    public static Map<String, Object> parseJSON2Map(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        if(StringUtils.isEmpty(jsonStr)) return map;
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    
    public static Map<String, Object> parseJSON2Map1(String jsonStr) throws JSONException {  
    	Map<String, Object> map = new HashMap<String, Object>();  
    	//最外层解析  
    	JSONObject json = JSONObject.fromObject(jsonStr);  
    	for(Object k : json.keySet()){  
    		Object v = json.get(k);   
    		map.put(k.toString(), v); 
    	}  
    	return map;  
    }  
    
    public static void main(String[] args) {
        String str = "{\"id\":2,\"catgName\":\"面食\",\"name\":\"少凤飞飞山\",\"num\":1,\"unit\":null,\"price\":23.00,\"remarks\":null},{\"id\":3,\"catgName\":\"面食\",\"name\":\"快餐\",\"num\":1,\"unit\":null,\"price\":2.33,\"remarks\":null},{\"id\":4,\"catgName\":\"面食\",\"name\":\"士大夫\",\"num\":1,\"unit\":null,\"price\":0.22,\"remarks\":null}";
        String aaData = aaData(str);
        System.out.println(aaData);
    }
}
