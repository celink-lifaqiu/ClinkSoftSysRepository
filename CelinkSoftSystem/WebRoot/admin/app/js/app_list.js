

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
				if(result){
				   $('#dg').datagrid('reload');
				   alert("上传成功");
				}else{
				   alert("上传失败");
				}
				
			}  
		});  
		    
   }