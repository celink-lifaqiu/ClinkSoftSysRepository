package com.manager.service.web.admin.dto;

public enum CommonResponse {
	/**
	 * 服务器异常
	 */
	INTERALSERVERERROR("500"),
	/**
	 * 操作失败
	 */
	OPERATIONFAILED("0"),
	
	/**
	 * 无权限
	 */
	FORBIDDEN("403"),
	/**
	 * 操作成功
	 */
	OPERATIONDONE("1"),
	
	/**
	 * 查询不到用户
	 */
	USERNOTFOUND("2"),
	
	/**
	 * 非法文件类型
	 */
	ILLEGELFILETYPE("3"),
	
	/**
	 * 群不存在
	 */
	GROUP_NOT_FOUND("4"),
	
	/**
	 * 群人数已达上限
	 */
	GROUP_FULL("5"),
	
	/**
	 * 用户已经存在群中
	 */
	USER_EXIST_IN_GROUP("6"),
	
	/**
	 * 已近加入圈子或活动
	 */
	HASJOINED("30"),
	/**
	 * 已发送好友邀请
	 */
	REPETITION("31"),
	/**
	 * 创建圈子或活动达到上限
	 */
	UPPERLIMIT("100"),
	/**
	 * 	上传数据日期错误
	 */
	UPLOADDATAERROR("101"),
	/**
	 * json格式错误
	 */
	JSONDATAERROR("300"),
	/**
	 * 未知主机
	 */
	UNKNOWNHOST("UnknownHost");
	
	 // 成员变量  
    private String responseCode;  
    // 构造方法  
    private CommonResponse(String responseCode) {  
        this.responseCode = responseCode;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.responseCode;  
    }  
}
