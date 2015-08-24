<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@include file="./common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/visitor.js"></script>
<title>南山版本系统</title>
</head>
<body>
	<div id="top">
		<div id="logo">
			<h3>CE-LINK</h3>
		</div>
		<div class="user-info">
			欢迎&nbsp;&nbsp;游客&nbsp;&nbsp; <a href="#" onclick="loginOut()"
				style="text-decoration:none;font-size: 16px; color: #FFF;">管理员登录</a>
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
				</div>
			</div>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<div class="easyui-tabs" id="tab"
				data-options="fit:true,border:false,plain:true"></div>
		</div>
	</div>
</body>
</html>
