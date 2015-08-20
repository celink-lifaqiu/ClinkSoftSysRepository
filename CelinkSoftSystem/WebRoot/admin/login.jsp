<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" session="false" %>   
<%@ page import="java.util.*"%>
<%@ include file="./common/common.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>南山版本管理系统登入</title>
	</head>
	<body>
		<div align="center">
			<div class="easyui-panel" title="后台管理登入"
				style="width: 230px; padding: 10px; left: 500px;">
				<form id="ff" action="login.do" method="post">
					<table>
						<tr>
							<td>
								用户名:
							</td>
							<td>
								<input class="easyui-validatebox" name="userName" type="text"
									 data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>
								密码：
							</td>
							<td>
								<input class="easyui-validatebox" name="userPwd" type="password"
									 data-options="required:true" />
							</td>
						</tr>

						<tr>
							<td>
								<input type="reset" value="重置">
							</td>
							<td>
								<input type="submit" value="确定"></input>
							</td>
						</tr>
					</table>
				</form>
			</div>


		</div>
		<script type="text/javascript">
$('#ff').form( {
		onSubmit: function(){  
      			 return $(this).form('validate');
   			 }, 
		success : function(data) {
   			var data = eval('(' + data + ')');
   			if(data.success){
   				window.location.href="main.jsp";
   			}else{
   				$.messager.alert('提示', data.message, 'error');
   			}
		}
	});

</script>
	</body>
</html>