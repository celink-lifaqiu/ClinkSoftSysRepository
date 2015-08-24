$(function() {
	$('#tt').tree({
		url:'getvisitormenu.do',
		animate:true,
		onContextMenu: function (e, node) {
            e.preventDefault();
            $(this).tree('select', node.target);
            $("#tmenu").menu('show', {
                left: e.pageX,
                top: e.pageY
            }).data("tabTitle", node.text);
        },
        onClick : function(node) {
			if (node.attributes == undefined) {
				//alert(node.text);
			} else {
				var pnode=$('#tt').tree('getParent',node.target);
				$.ajax({
					type : "POST",
					url : "resetResCode.do",
					data : "res_code=" + node.code,
					dataType : 'text',
					success : function(msg) {
														
					}
				});
				addTab1(pnode ? pnode.text + "-" + node.text : node.text, node.attributes.url, node.code);
				
			}
		},
		onAfterEdit : function(node) {
			resetName(node);
		},
		onLoadError:function(Error){
			$.messager.confirm('确认','加载出错,请重新登录?',function(r){    
			    if (r){    
			        window.location.href="visitor.do";  
			    }else{
			    	window.location.href="visitor.do";
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
        	}else if(item.name === "expandAll"){
        		$('#tt').tree('expandAll');
        	}else if(item.name === "collapseAll"){
        		$('#tt').tree('collapseAll');
        	}
        }
    });
    
    $('#tab').tabs({
        border:false,
        onSelect:function(title){
        	$.ajax({
				type : "POST",
				url : "resetResCode.do",
				data : "title=" + title,
				dataType : 'text',
				success : function(msg) {
													
				}
			});
        }
    });
});



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
	 location.href = "login.jsp";
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