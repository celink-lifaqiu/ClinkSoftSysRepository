<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" session="false"%>
<%@ page import="java.util.*"%>
<%@ include file="./common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>南山版本管理系统登入</title>
<link href="css/blue.css" type="text/css" rel="stylesheet"></link>

<link href="css/bugfree3_basic.css" type="text/css" rel="stylesheet"></link>
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	min-width: 980px;
	background-color: #F0F0F0;
	margin: 0px;
	padding: 0px;
	font: 12px Arial, Helvetica, sans-serif;
}

#logincontainer {
	height: 320px;
	border-width: 1px;
	border-style: solid;
	left: 50%;
	margin: -182px 0px 0px -265px;
	position: absolute;
	top: 50%;
	width: 560px;
	border-color: #B9E0F0;
	background-color: #EFFBFF;
}

#loginlogo {
	float: left;
	height: 283px;
	text-align: right;
	width: 190px;
	background: transparent url("images/seperate_line.png") no-repeat scroll
		right center;
}

#loginform-container {
	float: left;
	height: 280px;
	width: 340px;
}

#version {
	background: transparent url("images/logo_login.png") no-repeat scroll
		left bottom;
	color: #41697E;
	float: right;
	font-size: 11px;
	height: 48px;
	margin: 85px 20px 0px 0px;
	width: 156px;
}

.loginbutton {
	border: 0px none;
	color: #FFF;
	padding: 2px 20px;
}

.btn {
	cursor: pointer;
	font-size: 12px;
	margin-right: 1px;
	height: 24px;
	width: 85px;
}

.loginbutton {
	background-color: #4FC1ED;
}

#loginform-container .TxtInput {
	border-width: 1px;
	border-style: solid;
	font-size: 14px;
	font-weight: bold;
	height: 26px;
	line-height: 26px;
	margin-top: 0px;
	padding-left: 5px;
	width: 186px;
	border-color: #BCBCBC;
	color: #41697E;
}

#ff {
	margin: 20px 25px 0px 0px;
}
</style>
</head>
<body onload="init()">
	<div id="logincontainer">
		<div id="loginlogo">
			<span id="version"> </span>
		</div>
		<div id="loginform-container">
			<form id="ff" action="login.do" method="post">
				<table style="margin-left: 20px;">
					<tr>
						<td colspan="2"
							style="height:40px;padding: 0px;text-align: center;font-size: 24px;color: #00B7FF;">南山版本管理</td>
					</tr>
					<tr>
						<td style="text-align: right;"><label><strong
								id="ForTestUserName" style="font-size:14px;">用户名</strong> </label>
						</td>
						<td><input class="TxtInput" name="userName" id="userName"
							type="text" /></td>
					</tr>
					<tr>
						<td style="text-align: right;"><label><strong
								id="ForTestUserPWD" style="font-size:14px;">密码</strong> </label>
						</td>
						<td><input class="TxtInput" name="userPwd" id="userPwd"
							type="password" />
						</td>
					</tr>
					<tr>
						<td><label>&nbsp;</label>
						</td>
						<td><input id="saveCookie" value="" type="checkbox" /><span
							id="ForRememberMe" style="margin:20px 0 0">记住密码</span>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="登录" accesskey="L"
							class="loginbutton btn" />&nbsp;&nbsp;<input type="button" value="游客" accesskey="L"
							class="loginbutton btn" onclick="visitor()"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		window.onload = function init() {
			
			var box = getCookie("box_");
			var userName = getCookie("usn_");
			var userPwd = getCookie("pwd_");
			if (box) {
				ff.saveCookie.checked = true;
				ff.userName.value = userName;
				ff.userPwd.value = userPwd;
			}

		}
		$('#ff').form({
			onSubmit : function() {

				return $(this).form('validate');
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.success) {
					rememberMe();
					window.location.href = "main.jsp";
				} else {
					$.messager.alert('提示', data.message, 'error');
				}
			}
		});

		function rememberMe() {
			if (ff.saveCookie.checked) {
				SetCookie("usn_", ff.userName.value);
				SetCookie("pwd_", ff.userPwd.value);
				SetCookie("box_", true);
			} else {
				delCookie("box_");
			}
		}

		function visitor() {
			window.location.href = "visitor.do";
		}

		function SetCookie(name, value)//两个参数，一个是cookie的名子，一个是值
		{
			var Days = 300; //此 cookie 将被保存 30 天
			var exp = new Date(); //new Date("December 31, 9998");
			exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
			document.cookie = name + "=" + escape(value) + ";expires="
					+ exp.toGMTString();
		}
		function getCookie(name)//取cookies函数       
		{
			var arr = document.cookie.match(new RegExp("(^| )" + name
					+ "=([^;]*)(;|$)"));
			if (arr != null)
				return (arr[2]);
			return null;

		}
		function delCookie(name)//删除cookie
		{
			var exp = new Date();
			exp.setTime(exp.getTime() - 1);
			var cval = getCookie(name);
			if (cval != null)
				document.cookie = name + "=" + cval + ";expires="
						+ exp.toGMTString();
		}
	</script>
</body>
</html>