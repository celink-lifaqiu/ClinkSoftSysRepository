$(function() {
	$('#tt').tree({
		url:'getmenu.do',
		onContextMenu: function (e, title) {
            e.preventDefault();
            $("#tmenu").menu('show', {
                left: e.pageX,
                top: e.pageY
            }).data("tabTitle", title.text);
        },
        onClick : function(node) {
			if (node.attributes == undefined) {
				//alert(node.text);
			} else {
				var pnode=$('#tt').tree('getParent',node.target);
				addTab1(pnode ? pnode.text + "-" + node.text : node.text, node.attributes.url, node.code);
			}
		},
		onAfterEdit : function(node) {
			resetName(node);
		},
		onLoadError:function(Error){
			$.messager.confirm('确认','加载出错,请重新登录?',function(r){    
			    if (r){    
			        window.location.href="login.jsp";  
			    }else{
			    	window.location.href="login.jsp";
			    }
			});  
		} 
	}); 

	//绑定tabs的右键菜单
    $("#tab").tabs({
        onContextMenu : function (e, title) {
            e.preventDefault();
            $('#tmenu').menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data("tabTitle", title);
        }
    });
    //实例化menu的onClick事件
    $("#tmenu").menu({
        onClick : function (item) {
        	if(item.name === "closeSefl"){
        		closeSefl(this);
        	}else if(item.name === "closeOther"){
        		closeOther(this);
        	}else if(item.name === "closeAll"){
        		closeAll(this);
        	}else if(item.name === "addProject"){
        		// 检查是否有权限
        		
        		
        		
        		$.messager.alert('提示', '请双击重命名', 'info');
        	}else if(item.name === "addProjectProduct"){
        		$.messager.alert('提示', '请双击重命名', 'info');
        	}else if(item.name === "deleteProject"){
        		deleteNode();
        	}else if(item.name === "resetName"){
        		var node = $('#tt').tree('getSelected');
        		$('#tt').tree('beginEdit', node.target);
        	}
        }
    });
});


function resetName(node){
	if (window.confirm("确定重命名为"+node.text)) {
		$.ajax({  
	        type: "POST",  
	        url: "resetName.do",  
	        data: "name="+node.text+"&code="+node.code,  
	        dataType:'text',
	        success: function(msg){  
	      	  alert(msg); 
	      	  closeAll();
	      	  $("#tt").tree("reload");
	        }  
	    }); 
	}else{
		closeAll();
		$("#tt").tree("reload");
	}
	
}

function deleteNode(){  
    var node = $('#tt').tree('getSelected');  
    var pnode=$('#tt').tree('getParent',node.target);
    var text = pnode ? pnode.text + "下的" + node.text : node.text;
    if (node){  
    	if (window.confirm("删除"+text+"?")) {
	          $.ajax({  
	          type: "POST",  
	          url: "deleteNode.do",  
	          data: "code="+node.code,  
	          dataType:'text',
	          success: function(msg){  
	        	  alert(msg); 
	        	  closeAll();
	        	  $("#tt").tree("reload");
	          }  
	      });   
    			
    		} 
    }else{  
        alert("请选择要删除的节点");  
    }  
}  


function closeSefl(menu) {
	var curTabTitle = $(menu).data("tabTitle");
    var tab = $("#tab");
    tab.tabs("close", curTabTitle);
}

function closeOther(menu) {
	var curTabTitle = $(menu).data("tabTitle");
    var tabs = $("#tab");
	var allTabs = tabs.tabs("tabs");
	var closeTabsTitle = [];
	$.each(allTabs, function () {
        var opt = $(this).panel("options");
        if (opt.closable && opt.title != curTabTitle) {
            closeTabsTitle.push(opt.title);
        }
    });
    for (var i = 0; i < closeTabsTitle.length; i++) {
    	tabs.tabs("close", closeTabsTitle[i]);
    }
}
function closeAll(menu) {
	var curTabTitle = $(menu).data("tabTitle");
	var tabs = $("#tab");
	var allTabs = tabs.tabs("tabs");
	var closeTabsTitle = [];
	$.each(allTabs, function () {
		var opt = $(this).panel("options");
		if (opt.closable) {
			closeTabsTitle.push(opt.title);
		}
	});
	for (var i = 0; i < closeTabsTitle.length; i++) {
		tabs.tabs("close", closeTabsTitle[i]);
	}
}
function closeAll() {
	var tabs = $("#tab");
	var allTabs = tabs.tabs("tabs");
	var closeTabsTitle = [];
	$.each(allTabs, function () {
		var opt = $(this).panel("options");
		if (opt.closable) {
			closeTabsTitle.push(opt.title);
		}
	});
	for (var i = 0; i < closeTabsTitle.length; i++) {
		tabs.tabs("close", closeTabsTitle[i]);
	}
}

 
 
 
 function loginOut(){
	 location.href = "loginOut.do";
 }
 
 
 
 
 
function addTab1(name, url, code) {
	
	var u = url + '?code=' + code;
	if ($('#tab').tabs('exists', name)) {
		$('#tab').tabs('select', name);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="' + u + '" style="width:100%;height:100%;"></iframe>';
		$('#tab').tabs('add', {
			title : name,
			content : content,
			closable : true
		});
	}
}