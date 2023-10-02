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
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript">
		$(function (){
			$("#goods_parentid").change(function (){
				$.get(
						"GoodsTypeController",
						{"action":"getChildGoodsType",
							"gtype_parentid":$(this).val()
						},
						function (data){
							var jsArr=eval(data);
							$("#goods_fatherid").empty();
							for(var i=0;i<jsArr.length;i++){
								var jsObj=jsArr[i];

								var option=document.createElement("option");
								option.value=jsObj.id;
								option.innerText=jsObj.gtype_name;
								$("#goods_fatherid").append($(option));
							}
						}
				)
			})

			//失去焦点事件
			$("#goods_discount").blur(function (){
				var result=parseFloat($("#goods_price").val()) * $("#goods_discount").val();
				$("#goods_price_off").val(result.toFixed(2));//toFixed(2)保留两位小数
			})
		})
	</script>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>添加商品信息</span></div>
    <form action="GoodsInfoController?action=add" method="post" enctype="multipart/form-data">
    	<ul class="forminfo">
	    <li><label>商品名称</label><input name="goods_name" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
			<li><label>商品价格</label><input id="goods_price" name="goods_price" type="text" class="dfinput" /></li>
			<li><label>折扣</label><input id="goods_discount" name="goods_discount" type="text" class="dfinput" /></li>
			<li><label>促销价</label><input id="goods_price_off" name="goods_price_off" type="text" class="dfinput" /></li>
			<li><label>库存</label><input name="goods_stock" type="text" class="dfinput" /></li>
	    <li><label>所属大类</label>
			<select id="goods_parentid" name="goods_parentid">
				<c:forEach items="${parentGoodsType}" var="parentGoodsType">
					<option value="${parentGoodsType.id}">${parentGoodsType.gtype_name}</option>
				</c:forEach>
			</select>
	    </li>
	    <li><label>所属小类</label>
			<select id="goods_fatherid" name="goods_fatherid">
				<c:forEach items="${childGoodsTypes}" var="childGoodsType">
					<option value="${childGoodsType.id}">${childGoodsType.gtype_name}</option>
				</c:forEach>
			</select>
	    </li>
	     <li><label>商品图片</label><input name="goods_pic" type="file" /></li>
	    <li><label>商品描述</label><textarea rows="8" cols="40" name="goods_description" ></textarea></li>
	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    
    </form>
    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

