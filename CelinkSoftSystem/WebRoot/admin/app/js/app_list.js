
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
				   $('#dg_upload').datagrid('reload'); 
				   alert("修改成功");
				}else{
				   alert("修改失败");
				}
				
			}  
		});  
   }