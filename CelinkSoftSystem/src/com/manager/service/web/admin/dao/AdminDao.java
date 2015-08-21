package com.manager.service.web.admin.dao;

import java.util.List;
import java.util.Map;

import com.manager.service.web.admin.dto.Resource;
import com.manager.service.web.admin.dto.Version;

public interface AdminDao {
    /**
      * @Description: TODO(后台管理员登陆)  
      * @param params 用户名和密码
      * @return   
      * @throws
     */
	Map<String, Object> getAdmin(Map<String, Object> params);
    /**
      * @Description: TODO(后台管理界面菜单加载，权限控制)  
      * @param userCode
      * @return   
      * @throws
     */
	List<Map<String, Object>> getResource();

	List<Version> getVersions(Map<String, Object> params);
	int getVersionCount(Map<String, Object> map);
	int deleteVersions(int res_code);
	int deleteResource(int res_code);
	String getResourceName(int res_code);
	int updateResourceName(Map<String, Object> params);
	int uploadVersion(Map<String, Object> params);
	int updateVersion(Map<String, Object> params);
	int deleteVersion(int id);
	int findRescodeByPnameAndRname(Map<String, Object> params);
	int addResourceAndGetResCode(Resource resource);
}
