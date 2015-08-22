<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@include file="../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/app/js/app_list.js"></script>
</head>
<body >
	${result}
	<table id="dg" title="版本管理"> </table>
	
	<div id="dag" closed="true" class="easyui-dialog"
		style="z-index: 1; width: 450px; height: auto; padding: 10px 20px; top: 100px">
		<div style="padding: 10px 0 10px 60px">
			<form id="fm" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>文件名称:</td>
						<td><input type="hidden" id="id" type="text" name="id" /> 
						<input
							id="fileName" name="fileName" class="easyui-validatebox"
							type="text" required="true" style="width: 200px" />
						</td>
					</tr>
					<tr>
						<td>类型:</td>
						<td><select class="easyui-combobox" name="type" style="width:200px">
							<option value="0">正式版</option>
							<option value="1">定制版</option>
						</select></td>
					</tr>

					<tr>
						<td>版本号:</td>
						<td><input class="easyui-validatebox" id="version"
							class="text" name="versionCode" style="width: 200px"
							data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>备注说明:</td>
						<td>
						<textarea id="updateDesc" name="updateDesc" 
						style="width:200px;height:80px;">
						</textarea>
						
						</td>
					</tr>
					<tr>
						<td>CommitID:</td>
						<td><input class="easyui-validatebox" id="commitId"
							class="text" name="commitId" style="width: 200px"
							 />
						</td>
					</tr>
					<tr>
						<td>安装包:</td>
						<td><input class="easyui-filebox" id="installFile"
							type="file" name="installFile" style="width: 200px" />
						</td>
					</tr>
					<tr>
						<td id="saveButton"><a id="save" href="javascript:;"
							class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a>
						</td>
						<td><a href="javascript:;" class="easyui-linkbutton"
							iconCls="icon-cancel"
							onclick="javascript:$('#dag').dialog('close')">取消</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<div id="dag_upload" closed="true" class="easyui-dialog"
		style="z-index: 1; width: 450px; height: auto; padding: 10px 20px; top: 100px">
		<div style="padding: 10px 0 10px 60px">
			<form id="fm_upload" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>文件名称:</td>
						<td><input type="hidden" id="id_upload" type="text" name="id" />
							<input id="fileName" name="fileName" class="easyui-validatebox"
							type="text" required="true" style="width: 200px" />
						</td>
					</tr>
					<tr>
						<td>类型:</td>
						<td><select class="easyui-combobox" name="type"
							style="width:200px">
								<option value="0" selected="selected">正式版</option>
								<option value="1">定制版</option>
						</select></td>
					</tr>


					<tr>
						<td>版本号:</td>
						<td><input class="easyui-validatebox" id="version_upload"
							class="text" name="versionCode" style="width: 200px"
							data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>备注说明:</td>
						<td>
						<textarea id="updateDesc_upload" name="updateDesc" 
						style="width:200px;height:80px;">
						</textarea>
						
						</td>
					</tr>
					<tr>
						<td>commitId:</td>
						<td><input class="easyui-validatebox" id="commitId_upload"
							class="text" name="commitId" style="width: 200px"
							data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>上传文件:</td>
						<td><input class="easyui-filebox" id="installFile_upload"
							type="file" name="installFile" style="width: 200px" />
						</td>
					</tr>
					<tr>
						<td id="uploadButton"><a id="upload" href="javascript:;"
							class="easyui-linkbutton" iconCls="icon-ok" onclick="upload()">上传</a>
						</td>
						<td><a href="javascript:;" class="easyui-linkbutton"
							iconCls="icon-cancel"
							onclick="javascript:$('#dag_upload').dialog('close')">取消</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="tb" style="padding:5px;height: auto;">
	<div>
				类型: <select id="type_" class="easyui-combobox" panelHeight="auto" 
					style="width:100px">
					<option value="">请选择</option>
					<option value="0">正式版</option>
					<option value="1">定制版</option>
				</select> &nbsp;&nbsp;&nbsp;&nbsp;
				
				时间 从: <input id="startTime" class="easyui-datetimebox" style="width:150px"> 
				到:<input id="endTime" class="easyui-datetimebox" style="width:150px">
				<br><br> 
				版本号：<input id="versioncode_" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:200px">
				关键字： <input id="keyword_"  class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:200px"><br>
				
					<a href="javascript:void(0);"  id="btn-search" class="easyui-linkbutton" iconCls="icon-search" onclick="searchVersions()">查找</a> 
					<a href="javascript:void(0);" id="btn-reset" class="easyui-linkbutton" iconCls="icon-cancel" onclick="reset()">重置</a>
	
		</div>
	<div style="margin-bottom:5px">
	<%
   Object b = request.getParameter("code");
   session.setAttribute("code", b);
   
 	Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
 	String roleCode = user.get("role_code").toString();	
 	if("1".equals(roleCode)){
 		%>
 		<a href="javascript:void(0)" id="btn-edit" class="easyui-linkbutton" iconCls="icon-edit" onclick="editVersion()" plain="true">编辑版本</a>
            <a href="javascript:void(0)" id="btn-edit" class="easyui-linkbutton" iconCls="icon-add" onclick="addVersion()" plain="true">上传版本</a>
            <a href="javascript:void(0)" id="btn-edit" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteVersion()" plain="true">删除版本</a>
            
 		<% 
 	}	    
   %>
            <a href="javascript:void(0)" id="btn-edit" class="easyui-linkbutton" iconCls="icon-upload" onclick="downloadVersion()" plain="true">下载版本</a>
        </div>
		
		
	</div>
</body>
</html>


