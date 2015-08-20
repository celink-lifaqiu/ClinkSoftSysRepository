

	
	/**
	 * 根据条件查询用户信息
	 */
	search = function() {
		var type_ = $('#type_').val();
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
		var versioncode_ = $('#versioncode_').val();
		var keyword_ = $('#keyword_').val();
		$('#dg').datagrid('load', {
			keyword_ : keyword_,
			phone : phone
		});
	};
	

	/**
	 * 重置输入框中的查询参数
	 */
	reset = function() {
		if ($('#type_ option:selected') .val() != null) {
			$('#type_ option:selected') .val('');
		}
		if ($('#startTime').val()) {
			$('#startTime').val('');
		}
		if ($('#endTime').val()) {
			$('#endTime').val('');
		}
		if ($('#versioncode_').val()) {
			$('#versioncode_').val('');
		}
		if ($('#keyword_').val()) {
			$('#keyword_').val('');
		}
		return false;
	};
	
	
	function save(){
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
	
	function upload(){
		
		$.messager.progress(); 
	//	if()
		$('#fm_upload').form('submit',{  
			url: "uploadApp.do",  
			onSubmit: function(){
				return $(this).form('validate');  
			},  
			success: function(result){ 
				$.messager.progress('close');
			    $('#dag_upload').dialog('close');
				if(result){
				   $('#dg').datagrid('reload');
				   alert("修改成功");
				}else{
				   alert("修改失败");
				}
				
			}  
		});  
   }