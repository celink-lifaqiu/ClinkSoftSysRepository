<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@include file="./common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/main.js"></script>
<title>南山版本管理系统</title>
</head>
<body>
	<div id="top">
		<div id="logo">
			<h3>CE-LINK</h3>
		</div>
		<div class="user-info">
			<%
				Map<String, Object> user1 = (Map<String, Object>) session
						.getAttribute("user");
				if (user1 == null) {
					request.getRequestDispatcher("login.jsp");
				}

				String str = user1.get("real_name").toString();
				if ("1".equals(user1.get("role_code").toString())) {
			%>
			欢迎 <%=str%>&nbsp;&nbsp;<a href="#" onclick="loginOut()"
				style="text-decoration:none;font-size: 16px; color: #FFF;">退出</a>
			<%
				} 
			%>
		</div>
	</div>
	<div class="easyui-layout" style="width: 100%; height: 100%;">

		<div data-options="region:'west',split:true" title="菜单"
			style="width: 200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="项目列表" style="padding: 10px;background-color: #AFC8DD;">
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
						Map<String, Object> user = (Map<String, Object>) session
								.getAttribute("user");
						if ("1".equals(user.get("role_code").toString())) {
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
				data-options="fit:true,border:false,plain:true"></div>
		</div>
	</div>
	<div id="window_project" class="easyui-window" title="请输入项目名称"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width:500px;height:200px;padding:10px;">
		<div style="height:100px;padding:10px;" align='center'>
			请输入项目名称：<br> <input class="easyui-validatebox" id="projectName"
				name="projectName" type="text" data-options="required:true" /> <input
				id="btn" type="button" onclick="reName()" value="确定" />
		</div>
	</div>
	<div id="window_product" class="easyui-window" title="请输入项目名称"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width:500px;height:200px;padding:10px;">
		<div style="height:100px;padding:10px;" align='center'>
			请输入产品名称：<br> <input class="easyui-validatebox" id="projectName_"
				name="projectName_" type="text" data-options="required:true" /> <input
				id="btn" type="button" onclick="reName_()" value="确定" />
		</div>
	</div>
</body>
</html>
