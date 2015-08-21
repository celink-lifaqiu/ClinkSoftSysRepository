package com.manager.service.web.admin.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.service.web.admin.dao.AdminDao;
import com.manager.service.web.admin.dto.CommonResponse;
import com.manager.service.web.admin.dto.Resource;
import com.manager.service.web.admin.dto.ResultDto;
import com.manager.service.web.admin.dto.SearchDto;
import com.manager.service.web.admin.dto.Version;
import com.manager.service.web.admin.service.AdminService;
import com.wifiswitch.service.utils.EncryptUtils;

/**
 * AdminServiceImpl
 * @Decription:后台管理业务相关的接口实现类
 * @Author tim
 * @Date 2014-12-14 上午11:12:21
 * @Version 2.0
 */
@Service()
public class AdminServiceImpl implements AdminService {
 private final static Logger logger = Logger.getLogger(AdminServiceImpl.class);
   @Autowired
   private AdminDao adminDao;
   
   public Map<String, Object> getAdmin(String name, String pwd) {
	   Map<String, Object> params = new  HashMap<String, Object>();
	   params.put("name", name);
	   params.put("pwd", EncryptUtils.md5(pwd));
	   return adminDao.getAdmin(params);
   }

	@Override
	public List<Resource> getResource() {
		List<Map<String,Object>> list =adminDao.getResource();
		List<Resource> res=  new ArrayList<Resource>();
		for (Map<String,Object> map : list) {
			Resource resource = new Resource();
			resource.setResCode(Integer.parseInt(map.get("res_code")+""));
			resource.setResDesc(map.get("res_desc")+"");
			resource.setResName(map.get("res_name")+"");
			if(map.get("parent_code")!=null){
			   resource.setParentCode(Integer.parseInt(map.get("parent_code")+""));
			}
			resource.setResRank(Integer.parseInt(map.get("res_rank")+""));
			resource.setResUrl(map.get("res_url")+"");
			resource.setResStatus(Integer.parseInt(map.get("res_status")+""));
			res.add(resource);
		}
		return res;
	}
	
	@Override
	public String addMethodLog(String path) { 
		Map<String, Object> params = new HashMap<String, Object>();
		 try {
			 String encoding="UTF-8";
			File file=new File(path);
			if(file.isFile() && file.exists()){ //判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
				    if(lineTxt.indexOf("#com.lvtech.sps")!=-1){
				    	 String [] str =lineTxt.split("#"); 
				    	 params.put("visitTime",lineTxt.substring(0, lineTxt.indexOf(",")));
				    	 params.put("responseMS",str[1]);
				    	 params.put("method",str[2]);
				    	 //this.adminDao.addMethodLog(params);
				    }
				}
				read.close();
			}else{
				return CommonResponse.OPERATIONFAILED.toString();
			}
		} catch (Exception e) {
			return CommonResponse.OPERATIONFAILED.toString();
		}
		return CommonResponse.OPERATIONDONE.toString();
	}


	@Override
	public void addMethodLog(long time, String string) {
		
		
	}
	
	
	@Override
	public ResultDto getVersions(SearchDto dto, Map<String, Object> map) {
		ResultDto  resultDto = new ResultDto();
		map.put("startNum", (dto.getPage()-1)*dto.getRows());
		map.put("pageSize", dto.getRows());
		List<Version> list  = adminDao.getVersions(map);
		int total = adminDao.getVersionCount(map);
		resultDto.setRows(list);
		resultDto.initResultDto(dto.getRows(), dto.getPage(), total);
		return resultDto;
	}

	@Override
	public String deleteNode(int res_code) {
		String msg = "删除失败";
		
		int i = adminDao.deleteVersion(res_code);
		
		if(adminDao.deleteResource(res_code) > 0){
			msg = "删除成功";
		}
			
		return msg;
	}

	@Override
	public String getResourceNameByRescode(int res_code) {
		
		return adminDao.getResourceName(res_code);
	}

	@Override
	public int updateResourceName(int res_code, String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("res_code", res_code);
		params.put("res_name", name);
		return adminDao.updateResourceName(params);
	}

	
	@Override
	public int uploadVersion(Map<String, Object> params) {	
		
		return adminDao.uploadVersion(params);
	}
	
	@Override
	public int updateVersion(Map<String, Object> params) {		
		return adminDao.updateVersion(params);
	}

	@Override
	public int deleteVersion(int id) {
		return adminDao.deleteVersion(id);
	}

	@Override
	public int findCodeByTitle(String title) {
		String[] str = title.split("-");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p_name", str[0]);
		params.put("r_name", str[1]);
		return this.adminDao.findRescodeByPnameAndRname(params);
	}
	
}
