<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@include file="../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
   <%
   Object b = request.getParameter("code");
   session.setAttribute("code", b);
   
 	Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
 	Object roleCode = user.get("role_code");
					    
   %>
   var b = <%=roleCode %>;
   
   <%
    %>

$(function(){ 
	var $dg = $("#dg");
	$dg.datagrid({url:'getVersions.do'});
	if(b == "1"){
		$dg.datagrid({
			width : 1000,
			height : 'auto',
			fit : true,
			loadMsg : '数据加载中请稍后……',
			singleSelect : true,
			pagination : true,
			columns : [ [ { 
			field : 'ck', 
			checkbox:true, 
			width : 40 
			},{
				field : 'id',
				title : '编号',
				width : 50
			}, {
				field : 'typeStr',
				title : '类型',
				width : 80
			}, {
				field : 'fileName',
				title : '文件名称',
				width : 150
			}, {
				field : 'versionCode',
				title : '版本号',
				width : 80
			}, {
				field : 'size',
				title : '大小_KB',
				width : 80
			}, {
				field : 'createDate',
				title : '上传时间',
				width : 150
			}, {
				field : 'updateDesc',
				title : '更新说明',
				width : 300
			}, {
				field : 'commitId',
				title : 'commitId',
				width : 200
			} ] ],
			toolbar : [ {
				text : "编辑版本",
				iconCls : "icon-edit",
				handler : function() {
					$('#fm').form('clear');
					var rows = $('#dg').datagrid('getSelected');
					if (rows != null) {
						$('#dag').dialog('open').dialog('setTitle', '编辑版本');
						$('#fm').form('load', rows);
					} else {
						$.messager.alert('提示', '请选择要修改的版本', 'error');
					}
				}
			}, {
				text : "上传版本",
				iconCls : "icon-add",
				handler : function() {
					$('#fm_upload').form('clear');
					$('#dag_upload').dialog('open').dialog('setTitle', '上传版本');
					$('#fm_upload').form('load', rows);

				}
			}, {
				text : "删除版本",
				iconCls : "icon-remove",
				handler : function() {
					var rows = $('#dg').datagrid('getSelected');
					
					if (rows != null) {
						var id = rows["id"];
						var fileName=rows["fileName"];
						if (window.confirm("确认删除?")) {
							$.ajax({
									type : "POST",
									url : "deleteVersion.do",
									data : "id=" + id+'&'+"fileName="+fileName,
									dataType : 'text',
									success : function(msg) {
										if (msg) {
											$('#dg').datagrid('reload');
											alert("删除成功");
										} else {
											alert("删除失败");
										}								
									}
								});
						}
						
					} else {
						$.messager.alert('提示', '请选择要删除的版本', 'error');
					}
				}
			}, {
				text : "下载版本",
				iconCls : "icon-upload",
				handler : function() {
					var rows = $('#dg').datagrid('getSelected');
					if (rows != null) {
					var id = rows["id"];
					var fileName=rows["fileName"];
						$.ajax({
								type : "POST",
								url : "downloadVersion.do",
								data : "fileName="+fileName,
								dataType : 'text',
								success : function(url) {
									alert(url);
									window.location.href=url;						
									}
								});
					} else {
						$.messager.alert('提示', '请选择下载的版本', 'error');
					}
				}
			} ]
		});
	}else{
		$dg.datagrid({
			width : 1000,
			height : 'auto',
			fit : true,
			loadMsg : '数据加载中请稍后……',
			singleSelect : true,
			pagination : true,
			columns : [ [ { 
			field : 'ck', 
			checkbox:true, 
			width : 40 
			},{
				field : 'id',
				title : '编号',
				width : 50
			}, {
				field : 'typeStr',
				title : '类型',
				width : 80
			}, {
				field : 'fileName',
				title : '文件名称',
				width : 150
			}, {
				field : 'versionCode',
				title : '版本号',
				width : 80
			}, {
				field : 'size',
				title : '大小_KB',
				width : 80
			}, {
				field : 'createDate',
				title : '上传时间',
				width : 150
			}, {
				field : 'updateDesc',
				title : '更新说明',
				width : 300
			}, {
				field : 'commitId',
				title : 'commitId',
				width : 200
			} ] ],
			toolbar : [ {
				text : "下载版本",
				iconCls : "icon-upload",
				handler : function() {
					var rows = $('#dg').datagrid('getSelected');
					if (rows != null) {
					var id = rows["id"];
					var fileName=rows["fileName"];
						$.ajax({
								type : "POST",
								url : "downloadVersion.do",
								data : "fileName="+fileName,
								dataType : 'text',
								success : function(url) {
									window.location.href=url;						
									}
								});
					} else {
						$.messager.alert('提示', '请选择下载的版本', 'error');
					}
				}
			} ]
		});
	}
    
	
	
	
});



function reset(){
	$('#type_').combobox('setValue', '');
	$('#startTime').datetimebox('setValue', '');	
	$('#endTime').datetimebox('setValue', '');	

	if ($('#versioncode_').val()) {
		$('#versioncode_').val('');
	}
	if ($('#keyword_').val()) {
		$('#keyword_').val('');
	}
}

function searchVersions(){
	var type_ = $('#type_').combobox('getValue');	
	var startTime = $('#startTime').datetimebox('getValue');	
	var endTime = $('#endTime').datetimebox('getValue');	
	var keyword_ = $('#keyword_').val();	
	var versioncode_ = $('#versioncode_').val();		

	$('#dg').datagrid('load', {
			type_ : type_,
			startTime : startTime,
			endTime : endTime,
			keyword_ : keyword_,
			versioncode_ : versioncode_
		});


}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/app/js/app_list.js"></script>
</head>
<body>
	<table id="dg" title="版本管理" style="height:50px">
		<div id="tb" style="padding:10px 10px;">
		
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
			
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="searchVersions()">查找</a> 
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="reset()">重置</a>

		</div>
	</table>

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
								<option value="0">正式版</option>
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

</body>
</html>


