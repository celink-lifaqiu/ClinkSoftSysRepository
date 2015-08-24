<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@include file="../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/app/js/products.js"></script>
</head>
<body >
	${result}
	<table id="dg" title="版本浏览"> </table>
	
	
	<div id="tb" style="padding:5px;height: auto;">
	<div>
				类型: <select id="type_" class="easyui-combobox" panelHeight="auto" 
					style="width:75px">
					<option value="">请选择</option>
					<option value="0">研发内侧</option>
					<option value="1">正式发布</option>
					<option value="2">用户定制</option>
				</select> &nbsp;&nbsp;
				
				时间 从: <input id="startTime" class="easyui-datebox" style="width:90px"> 
				到:<input id="endTime" class="easyui-datebox" style="width:90px">
				&nbsp;&nbsp;
				版本号：<input id="versioncode_" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:100px">&nbsp;&nbsp;
				关键字： <input id="keyword_"  class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:100px"><br>
				
					<a href="javascript:void(0);"  id="btn-search" class="easyui-linkbutton" iconCls="icon-search" onclick="searchVersions()">查找</a> 
					<a href="javascript:void(0);" id="btn-reset" class="easyui-linkbutton" iconCls="icon-cancel" onclick="reset()">重置</a>
	
		</div>
	<div style="margin-bottom:5px">

            <a href="javascript:void(0)" id="btn-edit" class="easyui-linkbutton" iconCls="icon-upload" onclick="downloadVersion()" plain="true">下载版本</a>
        </div>
		
		
	</div>
</body>
</html>


