package com.manager.service.web.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.manager.service.web.admin.dto.Resource;
import com.manager.service.web.admin.dto.ResultDto;
import com.manager.service.web.admin.dto.SearchDto;

/**
 * @ClassName:AdminService
 * @Decription:后台管理业务相关的接口
 * @Author tim
 * @Date 2014-12-14 上午11:12:21
 * @Version 2.0
 */
public interface AdminService {
	
	Map<String, Object> getAdmin(String name, String pwd);

	List<Resource> getResource();
	
	/**
	  * 
	  * @Description: TODO(添加方法日志)  
	  * @param path
	  * @return   
	  * @throws
	 */
	String addMethodLog(String path);


	void addMethodLog(long time, String string);

	public ResultDto getVersions(SearchDto dto, Map<String, Object> map);

	public String deleteNode(int res_code,HttpServletRequest request);

	String getResourceNameByRescode(int res_code);

	int updateResourceName(int res_code, String name,HttpServletRequest request);
	
	public int uploadVersion(Map<String, Object> params);
	public int updateVersion(Map<String, Object> params);
	public int deleteVersion(int id);

	int findCodeByTitle(String title);

	void addProject(String name, String dir);

	void addProjectProduct(String pcode, String proName, String dir);

	boolean findFileNames(String fileName);
	
	String findFileNamebyId(int id);
	String getprojectNamesByName(Map<String, Object> params);

	String findDirByPCode(int res_code);

	String findDirByCode(int res_code);

	Integer findCodeById(int parseInt);
	
}
