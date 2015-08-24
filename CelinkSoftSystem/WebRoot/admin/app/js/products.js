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
			hidden : 'true',
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
			field : 'sizeStr',
			title : '大小',
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
			title : 'Commit ID',
			width : 200
		} ] ],
		
		toolbar : "#tb"
	});




 reset= function(){
	$('#type_').combobox('setValue', '');
	$('#startTime').datebox('setValue', '');	
	$('#endTime').datebox('setValue', '');	

	if ($('#versioncode_').val()) {
		$('#versioncode_').val('');
	}
	if ($('#keyword_').val()) {
		$('#keyword_').val('');
	}
}

 searchVersions = function(){
	var type_ = $('#type_').combobox('getValue');	
	var startTime = $('#startTime').datebox('getValue');	
	var endTime = $('#endTime').datebox('getValue');	
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
 
	
});