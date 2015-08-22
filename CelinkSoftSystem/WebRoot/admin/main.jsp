<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@include file="./common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/main.js"></script>
		<title>南山版本管理系统</title>
	</head>
	<body>
		<div class="easyui-layout" style="width: 100%; height: 95%;">
			<div data-options="region:'north'" style="background-color: #D6DED6;height: 95px;" >
				<div  style="margin-top: 20px;height: 78px; width:200px;float:left;background: url('images/celink.png') no-repeat ;"></div>
				<div  style="width: 80%;font-size:24px;text-align: center;">南山版本管理系统 </div>
				<div style="width: 98%;text-align: right;font-size:16px;">
					<%
					Map<String, Object> user1 = (Map<String, Object>)session.getAttribute("user");
					if(user1==null){
						request.getRequestDispatcher("login.jsp");
					}
					
					String str = user1.get("real_name").toString();
					if("1".equals(user1.get("role_code").toString())){
						
					%>
					管理员：<%=str %>&nbsp;&nbsp;<a href="#" onclick="loginOut()" style="text-decoration:none;font-size:16px;">注销</a>
					<%
					}else{
					%>
					游客：：<%=str %>&nbsp;&nbsp;<a href="#" onclick="loginOut()" style="text-decoration:none;font-size:16px;">注销</a>
					<%
					}
					%>
					 
				</div>
			</div>
			<div data-options="region:'south'" style="height: 50px;"></div>

			<div data-options="region:'west',split:true" title="菜单"
				style="width: 200px;">
				<div class="easyui-accordion" data-options="fit:true,border:false">
					<div title="项目列表" style="padding: 10px;background-color: #B5E1B5;">
						<ul id="tt">
						</ul> 
					</div>	
					<div id="tmenu" class="easyui-menu" style="width:150px;">  
					    <div name="expandAll">展开所有项目</div>  
					    <div name="collapseAll">收缩所有项目</div>   
					    <div name="closeSefl">关闭</div>  
					    <div name="closeOther">关闭其他</div>  
					    <div name="closeAll">关闭所有</div>
					    <%
					    	Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
					    	if("1".equals(user.get("role_code").toString())){
					    	
					    		%>
					    		<div name="resetName">重命名</div>
					    		<div name="deleteProject">删除该选项</div>
							    <div name="addProject">新增项目</div>
							    <div name="addProjectProduct">新增项目下的产品</div>
					    		<%
					    	}
					    
					     %>
					  </div>				
				</div>
			</div>
			<div data-options="region:'center',iconCls:'icon-ok'">
				<div class="easyui-tabs" id="tab"
					data-options="fit:true,border:false,plain:true">

				</div>
			</div>
		</div>

	</body>
</html>
