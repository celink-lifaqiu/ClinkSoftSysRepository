$(function(){ 
	var $dg = $("#dg");
	$dg.datagrid({url:'getVersions.do'});
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
		
		toolbar : "#tb"
	});
	




 reset= function(){
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

 searchVersions = function(){
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
 
 editVersion = function(){
	 $('#fm').form('clear');
		var rows = $('#dg').datagrid('getSelected');
		if (rows != null) {
			$('#dag').dialog('open').dialog('setTitle', '编辑版本');
			$('#fm').form('load', rows);
		} else {
			$.messager.alert('提示', '请选择要修改的版本', 'error');
		}
 }
 
 addVersion = function(){
	 $('#fm_upload').form('clear');
		$('#dag_upload').dialog('open').dialog('setTitle', '上传版本');
		$('#fm_upload').form('load', rows);
 }
 
 deleteVersion = function(){
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

 downloadVersion = function(){
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
 
 save = function(){

	 $.messager.progress(); 
	 $('#fm').form('submit',{  
		 url: "updateApp.do",  
		 onSubmit: function(){
			 return $(this).form('validate');  
		 },  
		 success: function(result){ 
			 $.messager.progress('close');
			 $('#dag').dialog('close');
			 if(result){
				 $('#dg').datagrid('reload'); 
				 alert("修改成功");
			 }else{
				 alert("修改失败");
			 }
			 
		 }  
	 });  
 }  
	
	upload = function(){
		
	//	$.messager.progress(); 
		 var str = document.getElementById("installFile_upload").value;		 		
		$('#fm_upload').form('submit',{  
			url: "uploadApp.do",  
			onSubmit: function(){	
				if(str==null||str.length==0){
					alert("请选择上传的文件");
					return false;
				}else{
				return $(this).form('validate');
				}
			},  
			success: function(result){ 
				$.messager.progress('close');
			    $('#dag_upload').dialog('close');
			    $('#dg').datagrid('reload');
				alert(result);
			}  
		});  
		    
   }
	
});