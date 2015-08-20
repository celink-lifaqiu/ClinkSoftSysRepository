package com.manager.service.web.admin.dto;

public class Version {
	private Integer id;
	/**
	 * 发布时间
	 */
	private String createDate;
	/**
	 * 大小(bytes)
	 */
	private int size;
	
	private String commitId;
	
	
	private int resCode;
	/**
	 * 版本号
	 */
	private String versionCode;
	
	
	private String saveDir;
	/**
	 * 更新说明
	 */
	private String updateDesc;

	/**
	 * 0：正式版 1：定制版
	 */
	private int type;
	
	public String getTypeStr(){
		return this.getType()==0?"正式版":"定制版";
	}
	
	/**
	 * 下载地址
	 */
	private String downloadURL;
	
	/**
	 * 文件名
	 */
	private String fileName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getCommitId() {
		return commitId;
	}

	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public String getUpdateDesc() {
		return updateDesc;
	}

	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
