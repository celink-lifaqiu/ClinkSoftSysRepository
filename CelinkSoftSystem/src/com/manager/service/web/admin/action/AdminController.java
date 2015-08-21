package com.manager.service.web.admin.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.manager.service.web.admin.dao.AdminDao;
import com.manager.service.web.admin.dto.CommonResponse;
import com.manager.service.web.admin.dto.Message;
import com.manager.service.web.admin.dto.Resource;
import com.manager.service.web.admin.dto.ResultDto;
import com.manager.service.web.admin.dto.SearchDto;
import com.manager.service.web.admin.service.AdminService;
import com.wifiswitch.service.utils.JsonUtil;



@Controller
@RequestMapping("/admin")
public class AdminController {
	private final static Logger logger = Logger.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;	
	
	/**
	 * @throws UnsupportedEncodingException 
	  * @Description: TODO(后台用户登陆)  
	  * @param request
	  * @param r   
	  * @throws
	 */
	@RequestMapping("/login")
	public void userLogin(HttpServletRequest request,
			HttpServletResponse r) {
		r.setCharacterEncoding("utf8");
		
		if(request.getSession().getAttribute("user") != null){
			request.getSession().removeAttribute("user");
		}
		
		String name = request.getParameter("userName");
		String pwd =request.getParameter("userPwd");
		System.out.println(name);
		try{
			Map<String, Object> admin=adminService.getAdmin(name,pwd);
			Message message = new Message(false, "用户名或密码错误");
			if(admin!=null){
				request.getSession().setAttribute("user", admin);
				message = new Message(true, "登入成功");
			}
			 r.getWriter().write(JsonUtil.getJson(message));
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	@RequestMapping("/loginOut")
	public void loginOut(HttpServletRequest request,
			HttpServletResponse r) throws IOException, ServletException {
		if(request.getSession().getAttribute("user") != null){
			request.getSession().removeAttribute("user");
		}
		request.getRequestDispatcher("login.jsp").forward(request, r);
	}
	/**
	  * @Description: TODO(后台用户菜单加载)  
	  * @param request
	  * @param response
	  * @throws Exception   
	  * @throws
	 */
	@RequestMapping("/getmenu")
	public void getMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		try {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Map<String, Object> admin = (Map) request.getSession().getAttribute("user");
			List<Resource> resources = adminService.getResource();
			List<Resource> parent = new ArrayList<Resource>();
			List<Resource> soon = new ArrayList<Resource>();
			Resource r = null;
			for (int i = 0; i < resources.size(); i++) {
				r = resources.get(i);
				if (r.getResRank() == 1) {
					parent.add(r);
				} else if (r.getResRank() == 2) {
					soon.add(r);
				}
			}
			String menu = null;
			menu = concatMenu(parent, soon);
			System.out.println(menu);
			response.getWriter().write(menu);
		} catch (Exception e) {
			logger.info("获取菜单栏出错：" + e.getLocalizedMessage());
			throw new Exception();
		}
	}

	// 拼接左侧菜单
	private String concatMenu(List<Resource> parent, List<Resource> soon) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Resource r = null;
		for (int i = 0; i < parent.size(); i++) {
			r = parent.get(i);
			sb.append("{");
			sb.append("\"text\":").append("\"").append(r.getResName()).append(
					"\"").append(",");
			sb.append("\"code\":").append(r.getResCode()).append(",");
			sb.append("\"isexpand\":false, \"children\":");
			sb.append(getsubMenu(r, soon));
			sb.append("}");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

	private String getsubMenu(Resource resource, List<Resource> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Resource r = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				r = list.get(i);
				if (resource.getResCode().equals(r.getParentCode())) {
					sb.append("{\"text\":").append("\"").append(r.getResName())
							.append("\"").append(",");
					sb.append("\"code\":").append(r.getResCode()).append(",");
					sb.append("\"attributes\":{\"url\":\"").append(
							"app/app_list.jsp").append("\"}").append("},");

				}

			}
		}
		if (sb.length() > 1) {
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 
	  * @Description: TODO(查询app信息)  
	  * @param dto
	  * @param request
	  * @param response   
	  * @throws
	 */
	@RequestMapping("/app/getVersions")
	public void searchApp(SearchDto dto,HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", request.getParameter("type_"));
			map.put("startTime", request.getParameter("startTime"));
			map.put("endTime", request.getParameter("endTime"));
			map.put("keyword_", ("".equals(request.getParameter("keyword_")) || request.getParameter("keyword_")==null) ? "" : "%"+request.getParameter("keyword_")+"%");
			map.put("versioncode_", ("".equals(request.getParameter("versioncode_")) || request.getParameter("versioncode_")==null) ? "" : "%"+request.getParameter("versioncode_")+"%");
			map.put("res_code", request.getSession().getAttribute("code").toString());
			
			ResultDto resultDto=adminService.getVersions(dto, map);
			response.getWriter().write(JsonUtil.getJson(resultDto));
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("获取信息出错：" + e.getLocalizedMessage());
		}
		//request.getSession().removeAttribute("code");
	}
	
	@RequestMapping("/deleteNode")
	public void deleteNode(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String msg = "";
		String code = request.getParameter("code");
		if(code==null || "".equals(code)){
			msg = "id不能为空，删除失败。";
		}else{
			int res_code = Integer.parseInt(code);
			msg = this.adminService.deleteNode(res_code);
		}
		
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/resetName")
	public void resetName(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String msg = "";
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		if(name==null || "".equals(name)){
			msg = "重命名不能为空，修改失败。";
		}else{
			int res_code = Integer.parseInt(code);
			String res_name = this.adminService.getResourceNameByRescode(res_code);
			if(res_name.equals(name.trim())){
				msg = "不能和原来名称一样，修改失败。";
			}else{
				int i = this.adminService.updateResourceName(res_code, name);
				if(i > 0){
					msg = "修改成功";
				}else{
					msg = "修改失败";
				}
			}
		}
		
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/resetResCode")
	public void resetResCode(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		request.getSession().removeAttribute("code");
		
		if(("".equals(request.getParameter("res_code")) || request.getParameter("res_code") == null)){
			String title = request.getParameter("title");
			int code = this.adminService.findCodeByTitle(title);
			request.getSession().setAttribute("code", code);	
		}else{
			request.getSession().setAttribute("code", request.getParameter("res_code").toString());	
		}
		try {
			System.out.println("当前选择的是："+request.getSession().getAttribute("code"));
			response.getWriter().write("suc");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addProject")
	public void addProject(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		
		this.adminService.addProject();
		
		try {
			response.getWriter().write("suc");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addProjectProduct")
	public void addProjectProduct(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String pcode = request.getParameter("pcode");
		this.adminService.addProjectProduct(pcode);
		
		try {
			response.getWriter().write("suc");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	

	@RequestMapping("/app/uploadApp")
	public void uploadApp(
			@RequestParam(value = "installFile", required = false) MultipartFile filedata,
			HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		int result=0;		
		response.setCharacterEncoding("UTF-8");								
		if (filedata != null && !filedata.isEmpty()) {//没有文件不让上传
			Map<String, Object> appInfo = new HashMap<String, Object>();
			String type = request.getParameter("type");
			String fileName = request.getParameter("fileName");
			String versionCode = request.getParameter("versionCode");
			String updateDesc = request.getParameter("updateDesc").toString();
			String commitId = request.getParameter("commitId");
			int res_code = Integer.parseInt(request.getSession()
					.getAttribute("code").toString());
			appInfo.put("type", type);
			appInfo.put("fileName", fileName);
			appInfo.put("versionCode", versionCode);
			appInfo.put("updateDesc", updateDesc);
			appInfo.put("commitId", commitId);
			appInfo.put("res_code", res_code);
			
			String path = request.getSession().getServletContext().getRealPath("")
					+ File.separator +"admin"+File.separator+ "appFile";
			File file=new File(path);			
	        if(!file.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
	        	file.mkdirs();
	        }
			File targetFile = new File(path, fileName);
			String saveDir = "appFile";
			int size = Integer.parseInt((filedata.getSize() / 1024) + 1 + "");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createDate = df.format(new Date());
			appInfo.put("createDate", createDate);
			appInfo.put("size", size);
			appInfo.put("saveDir", saveDir);
			filedata.transferTo(targetFile);
			result = adminService.uploadVersion(appInfo);
		}
		 
		try {
			if (result > 0) {
				response.getWriter().write(JsonUtil.getJson(Boolean.TRUE));
			} else {
				response.getWriter().write(JsonUtil.getJson(Boolean.FALSE));
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取信息出错：" + e.getLocalizedMessage());
		}
		// request.getSession().removeAttribute("code");
	}

	@RequestMapping("/app/updateApp")
	public void updateApp(
			@RequestParam(value = "installFile", required = false) MultipartFile filedata,
			HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> appInfo = new HashMap<String, Object>();
		String type = request.getParameter("type");

		String fileName = request.getParameter("fileName");
		String versionCode = request.getParameter("versionCode");
		String updateDesc = request.getParameter("updateDesc");
		String commitId = request.getParameter("commitId");
		int id = Integer.parseInt(request.getParameter("id"));
		int res_code = Integer.parseInt(request.getSession()
				.getAttribute("code").toString());
		appInfo.put("type", type);
		appInfo.put("fileName", fileName);
		appInfo.put("versionCode", versionCode);
		appInfo.put("updateDesc", updateDesc);
		appInfo.put("commitId", commitId);
		appInfo.put("res_code", res_code);
		appInfo.put("id", id);

		String path = request.getSession().getServletContext().getRealPath("")
				+ File.separator + "admin" + File.separator + "appFile";
		if (filedata != null && !filedata.isEmpty()) {
			File targetFile = new File(path, fileName);
			String saveDir = "appFile";
			int size = Integer.parseInt((filedata.getSize() / 1024) + 1 + "");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createDate = df.format(new Date());
			appInfo.put("createDate", createDate);
			appInfo.put("size", size);
			appInfo.put("saveDir", saveDir);
			filedata.transferTo(targetFile);
		}
		int result = adminService.updateVersion(appInfo);

		try {
			if (result > 0) {
				response.getWriter().write(JsonUtil.getJson(Boolean.TRUE));
			} else {
				response.getWriter().write(JsonUtil.getJson(Boolean.FALSE));
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取信息出错：" + e.getLocalizedMessage());
		}
		// request.getSession().removeAttribute("code");
	}

	@RequestMapping("/app/deleteVersion")
	public void deleteVersion(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		response.setCharacterEncoding("UTF-8");		
		int id = Integer.parseInt(request.getParameter("id"));
		int result = adminService.deleteVersion(id);

		try {
			if (result > 0) {
				String root = request.getSession().getServletContext().getRealPath("")
						+ File.separator +"admin"+File.separator+ "appFile" +File.separator+ request.getParameter("fileName").toString();
				System.out.println(root);
				File file = new File(root);
				file.delete();
				response.getWriter().write(JsonUtil.getJson(Boolean.TRUE));
			} else {
				response.getWriter().write(JsonUtil.getJson(Boolean.FALSE));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取信息出错：" + e.getLocalizedMessage());
		}
	}
	@RequestMapping("/app/downloadVersion")
	public void downloadVersion(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		response.setContentType("application/octet-stream");  
		String fileName=request.getParameter("fileName");
		String downpath = getPropertyByName("config.properties", "download.url")+fileName;
		response.getWriter().write(downpath);	
	}
	
	// 方法一：通过java.util.ResourceBundle读取资源属性文件  
    public String getPropertyByName(String path, String name) {  
        String result = "";  
        
        try {  
        	Properties props=PropertiesLoaderUtils.loadAllProperties("config.properties");
            // 方法一：通过java.util.ResourceBundle读取资源属性文件  
            result = props.getProperty(name);
        } catch (Exception e) {  

        }  
        return result;  
    }  
	
}
