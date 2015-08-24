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
		// 根据res_code找出资源，判断是一级还是二级
		Map<String, Object> resource = this.adminDao.findResourceByRescode(res_code);
		
		// 如果是二级，那么根据res_code删除表softversion_tb下的记录，在根据res_code删除表resource的记录
		if(Integer.parseInt(resource.get("res_rank").toString())==2){
			// 根据res_code删除表softversion_tb下的记录
			adminDao.deleteVersions(res_code);
			// 根据res_code删除表resource的记录
			if(adminDao.deleteResource(res_code) > 0){
				msg = "删除成功";
			}
		}else{
			Map<String, Object> params = new HashMap<String, Object>();
			// 如果是一级，先根据res_code查找出它的所有二级res_code
			List<Integer> list = this.adminDao.findRescodesByparentcode(res_code);
			// 把自己加入res_code
			list.add(res_code);
			params.put("rescodes", list);
			// 在表softversion_tb下删除所有二级res_code的记录
			adminDao.deleteVersionByRescodes(params);
			// 在表resource中删除list的记录
			if(adminDao.deleteResources(params) > 0){
				msg = "删除成功";
			}
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

	@Override
	public void addProject(String name) {
		Resource resource = new Resource();
		resource.setResRank(1);
		resource.setResStatus(0);
		this.adminDao.addResourceAndGetResCode(resource);
		int res_code = resource.getResCode();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("res_code", res_code);
		params.put("res_name", name);
		this.adminDao.updateResourceName(params);
		
	}

	@Override
	public void addProjectProduct(String pcode) {
		Resource resource = new Resource();
		resource.setResRank(2);
		resource.setParentCode(Integer.parseInt(pcode));
		resource.setResStatus(0);
		this.adminDao.addResourceAndGetResCode(resource);
		int res_code = resource.getResCode();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("res_code", res_code);
		params.put("res_name", res_code + "请重命名");
		this.adminDao.updateResourceName(params);
	}

	@Override
	public boolean findFileNames(String fileName) {

		List<String> fileNames = this.adminDao.findFileNames(fileName);
		if(fileNames != null && fileNames.size() > 0){
			return true;
		}
		
		return false;
	}
	
	
	
}
