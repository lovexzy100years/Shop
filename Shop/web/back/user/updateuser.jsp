<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>编辑用户信息</span></div>
    <form action="UserController?action=modify" method="post">
    	<input type="hidden" name="username" value="${user.username}" />
    	<ul class="forminfo">
			<!--<li><label>用户名</label></li>-->
	    <li><label>用户名</label><input name="username" type="text" class="dfinput" value="${user.username}" disabled="disabled"/></li>
	    <!--<li><label>密码</label><input name="password" type="text" class="dfinput" value="" /></li>-->
			<li><label>电话</label><input name="phone" type="text" class="dfinput" value="${user.phone}" /></li>


	    <li>
		    <label>性别</label>
		     <select name="sex" class="dfinput">
			    	<option value="男" <c:if test="${user.sex eq '男'}">selected="selected"</c:if>>男</option>
			    	<option value="女" <c:if test="${user.sex eq '女'}">selected="selected"</c:if>>女</option>
		    </select>
	    </li>


	  	<li>
	  	<label>角色</label>
	  		<select name="is_admin" class="dfinput">
		    	<option value="是" <c:if test="${user.is_admin eq '是'}">selected="selected"</c:if>>管理员</option>
		    	<option value="否" <c:if test="${user.is_admin eq '否'}">selected="selected"</c:if>>普通用户</option>
	    </select>
	    </li>
	    &nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    </form>
    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

