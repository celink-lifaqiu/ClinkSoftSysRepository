package com.manager.service.web.admin.dto;

public class Resource {
	private Integer resCode; //资源ID
	private String resName; //资源名称
	private String resDesc; //资源描述
	private String resUrl; //资源地址
	private Integer resStatus; //状态
	private Integer parentCode; //上级资源
	private Integer resRank; //资源等级
	public Integer getResCode() {
		return resCode;
	}
	public void setResCode(Integer resCode) {
		this.resCode = resCode;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResDesc() {
		return resDesc;
	}
	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	public Integer getResStatus() {
		return resStatus;
	}
	public void setResStatus(Integer resStatus) {
		this.resStatus = resStatus;
	}
	public Integer getParentCode() {
		return parentCode;
	}
	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}
	public Integer getResRank() {
		return resRank;
	}
	public void setResRank(Integer resRank) {
		this.resRank = resRank;
	}

	@Override
	public String toString() {
		return "{\"parentCode\":\"" + parentCode + "\", \"resCode\":\""
				+ resCode + "\", \"resDesc\":\"" + resDesc
				+ "\", \"resName\":\"" + resName + "\", \"resRank\":\""
				+ resRank + "\", \"resStatus\":\"" + resStatus
				+ "\", \"resUrl\":\"" + resUrl + "\"}";
	}
}
