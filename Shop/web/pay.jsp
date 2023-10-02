<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0 ,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<title>结算页面</title>

	<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />

	<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />
	<link href="css/cartstyle.css" rel="stylesheet" type="text/css" />

	<link href="css/jsstyle.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript">

		$(function(){
			$("ul[class='addressChoose'] li").each(function(){
				$(this).unbind();
				$(this).click(function(){
					$("ul[class='addressChoose'] li").removeClass("defaultAddr");
					$(this).addClass("defaultAddr");
					var shouhuoren = $("li[class='user-addresslist defaultAddr'] span[class='buy-user']").html();
					var dianhua = $("li[class='user-addresslist defaultAddr'] span[class='buy-phone']").html();
					var dizhi = $("li[class='user-addresslist defaultAddr'] span[class='buy--address-detail']").html();
					$("#buy_user").val(shouhuoren);
					$("#o_shperson").val(shouhuoren);
					$("#o_shphone").val(dianhua);
					$("#o_shaddress").val(dizhi);

					$("#send").html(dizhi);
					$("#person").html(shouhuoren);
					$("#tel").html(dianhua);
				})
			})

			$("ul[class='op_express_delivery_hot'] li").each(function(){
				$(this).unbind();
				$(this).click(function(){
					var express = $(this).children("span").attr("id");
					alert(express);
					$("#express").val(express);
					$("ul[class='op_express_delivery_hot'] li").removeClass("selected");
					$(this).addClass("selected");
					$("#o_sendtype").val(express);
				})
			})

			$("ul[class='pay-list'] li").each(function(){
				$(this).unbind();
				$(this).click(function(){
					var paytype = $(this).children("span").attr("id");
					// alert(paytype);
					$("#paytype").val(paytype);
					$("ul[class='pay-list'] li").removeClass("selected");
					$(this).addClass("selected");
					$("#o_paytype").val(paytype);
				})
			})


			$("#go").click(function(){
				$("#form").submit();
			})

			$("li[class='user-addresslist defaultAddr']").trigger("click");

			$("input[id^='reduce_']").each(function () {
				$(this).click(function () {
					var id = $(this).attr("id").split("_")[1];//商品id
					var count = parseInt($("#value_" + id).val());

					if (count > 1) {
						count--;
						$.get(
								"ShopController?action=update",
								{"id": id, "count": count},
								function (data) {

									$("#value_" + id).val(count);
									var jsObj = eval("(" + data + ")");

									$("#shopCarItemMoney" + id).text(jsObj.money);
									$("#J_ActualFee").text(jsObj.allMoney);
								}
						)
					}
				})
			})


			$("input[id^='add']").each(function () {
				$(this).click(function () {

					var goods_stock = parseInt($(this).attr("name"));

					var id = $(this).attr("id").split("_")[1];//商品id
					var count = parseInt($("#value_" + id).val());
					if (count < goods_stock) {
						count++;
						$.get(
								"ShopController?action=update",
								{"id": id, "count": count},
								function (data) {

									$("#value_" + id).val(count);
									var jsObj = eval("(" + data + ")");

									$("#shopCarItemMoney" + id).text(jsObj.money);
									$("#J_ActualFee").text(jsObj.allMoney);
								}
						)
					}
				})
			})

			$("input[id^='value_']").each(function () {
				$(this).blur(function () {

					var goods_stock = parseInt($(this).attr("name"));
					var id = $(this).attr("id").split("_")[1];//商品id
					var count = parseInt($(this).val());

					if(count < 1){
						count = 1;
					}
					if(count > goods_stock){
						count = goods_stock;
					}
					$(this).val(count);

					$.get(
							"ShopController?action=update",
							{"id":id,"count":count},
							function (data) {

								$("#value_" + id).val(count);
								var jsObj = eval("(" + data + ")");

								$("#shopCarItemMoney" + id).text(jsObj.money);
								$("#J_ActualFee").text(jsObj.allMoney);
							}
					)
				})

			})

		})



	</script>

</head>

<body>

<%@include file="head.jsp" %>
<div class="concent">
	<!--地址 -->
	<div class="paycont">
		<div class="address">
			<h3>确认收货地址 </h3>
			<div class="control">
				<div class="tc-btn createAddr theme-login am-btn am-btn-danger"><a href="addAddress.jsp" style="color: white;">使用新地址</a></div>
			</div>
			<div class="clear"></div>
			<ul class="addressChoose">

				<c:forEach items="${addresses}" var="addresse">
					<!-- 默认地址：class="user-addresslist defaultAddr" -->
					<li class="user-addresslist <c:if test="${addresse.isdefault eq '是'}">defaultAddr</c:if>">
						<div class="address-left">
							<div class="user DefaultAddr">
								<span class="buy-address-detail"></span>
								<span>收货人</span>
								<span class="buy-user">${addresse.shouhuoren}</span>
							</div>
							<div class="user DefaultAddr">
								<span class="buy-address-detail"></span>
								<span>电话</span>
								<span class="buy-phone">${addresse.phone}</span>
							</div>

							<div class="default-address DefaultAddr">
								<span class="buy-line-title buy-line-title-type">收货地址：</span>
								<span class="buy--address-detail">${addresse.address}</span>
							</div>
							<c:if test="${addresse.isdefault eq '是'}">
								<ins class="deftip">默认地址</ins>
							</c:if>
						</div>
						<div class="new-addr-btn">
							<span class="new-addr-bar hidden">|</span>
							<a href="AddressController?action=initModify&id=${addresse.id}">编辑</a>
							<span class="new-addr-bar">|</span>
							<a href="AddressController?action=delete&id=${addresse.id}&userId=${userId}">删除</a>

						</div>
					</li>
				</c:forEach>
			</ul>

			<div class="clear"></div>
		</div>
		<!--物流 -->
		<div class="logistics">
			<h3>选择物流方式</h3>
			<ul class="op_express_delivery_hot">
				<li data-value="yuantong" class="OP_LOG_BTN "><i class="c-gap-right" style="background-position:0px -468px"></i>圆通<span id="圆通"></span></li>
				<li data-value="shentong" class="OP_LOG_BTN "><i class="c-gap-right" style="background-position:0px -1008px"></i>申通<span id="申通"></span></li>
				<li data-value="yunda" class="OP_LOG_BTN "><i class="c-gap-right" style="background-position:0px -576px"></i>韵达<span id="韵达"></span></li>
				<li data-value="zhongtong" class="OP_LOG_BTN op_express_delivery_hot_last"><i class="c-gap-right" style="background-position:0px -324px"></i>中通<span id="中通"></span></li>
				<li data-value="shunfeng" class="OP_LOG_BTN  op_express_delivery_hot_bottom"><i class="c-gap-right" style="background-position:0px -180px"></i>顺丰<span id="顺丰"></span></li>
			</ul>
		</div>
		<div class="clear"></div>

		<!--支付方式-->
		<div class="logistics">
			<h3>选择支付方式</h3>
			<ul class="pay-list">
				<li class="pay card"><img src="images/wangyin.jpg" />银联<span id="银联"></span></li>
				<li class="pay qq"><img src="images/weizhifu.jpg" />微信<span id="微信"></span></li>
				<li class="pay taobao"><img src="images/zhifubao.jpg" />支付宝<span id="支付宝"></span></li>
			</ul>
		</div>
		<div class="clear"></div>

		<!--订单 -->
		<div class="concent">
			<div id="payTable">
				<h3>确认订单信息</h3>
				<div class="cart-table-th">
					<div class="wp">

						<div class="th th-item">
							<div class="td-inner">商品信息</div>
						</div>
						<div class="th th-price">
							<div class="td-inner">单价</div>
						</div>
						<div class="th th-amount">
							<div class="td-inner">数量</div>
						</div>
						<div class="th th-sum">
							<div class="td-inner">金额</div>
						</div>
						<div class="th th-oplist">
							<div class="td-inner">配送方式</div>
						</div>

					</div>
				</div>
				<div class="clear"></div>

				<c:forEach items="${shopCar.shopCarItems}" var="shopCarItem">
					<!-- 购物车 -->
					<tr class="item-list">
						<div class="bundle  bundle-last">

							<div class="bundle-main">
								<ul class="item-content clearfix">
									<div class="pay-phone">
										<li class="td td-item">
											<div class="item-pic">
												<a href="#" class="J_MakePoint">
													<img src="images/${shopCarItem.goodsInfo.goods_pic}" style="width: 80px; height: 80px" class="itempic J_ItemImg"></a>
											</div>
											<div class="item-info">
												<div class="item-basic-info">
													<a href="#" class="item-title J_MakePoint" data-point="tbcart.8.11">${shopCarItem.goodsInfo.goods_name}</a>
												</div>
											</div>
											<div class="item-info">
												<div class="item-basic-info">
													<a href="#" class="item-title J_MakePoint" data-point="tbcart.8.11">${shopCarItem.goodsInfo.goods_description}</a>
												</div>
											</div>
										</li>
										<li class="td td-price">
											<div class="item-price price-promo-promo">
												<div class="price-content">
													<div class="price-line">
														<em class="price-original">${shopCarItem.goodsInfo.goods_price}</em>
													</div>
													<div class="price-line">
														<em class="J_Price price-now" id="priceNow" tabindex="0">${shopCarItem.goodsInfo.goods_price_off}</em>
													</div>
												</div>
											</div>
										</li>
									</div>
									<li class="td td-amount">
										<div class="amount-wrapper ">
											<div class="item-amount ">
												<span class="phone-title">购买数量</span>
												<div class="sl">
													<input class="min am-btn" name="" id="reduce_${shopCarItem.goodsInfo.id}" type="button" value="-"/>
													<input class="text_box" name="${shopCarItem.goodsInfo.goods_stock}" id="value_${shopCarItem.goodsInfo.id}" type="text" value="${shopCarItem.count}" style="width: 30px; text-align: center;" />
													<input class="add am-btn" name="${shopCarItem.goodsInfo.goods_stock}" id="add_${shopCarItem.goodsInfo.id}" type="button" value="+"  />
													<input type="hidden"  value="goodsId">
												</div>
											</div>
										</div>
									</li>
									<li class="td td-sum">
										<div class="td-inner">
											<em tabindex="0" class="J_ItemSum number" id="shopCarItemMoney${shopCarItem.goodsInfo.id}">${shopCarItem.money}</em>
										</div>
									</li>
								</ul>
								<div class="clear"></div>

							</div>
							</div>
					</tr>

				</c:forEach>

				<div class="clear"></div>
			</div>


			<!--信息 -->
			<div class="order-go clearfix">
				<div class="pay-confirm clearfix">
					<div class="box">
						<div tabindex="0" id="holyshit267" class="realPay"><em class="t">实付款：</em>
							<span class="price g_price ">
                                    <span>¥</span> <em class="style-large-bold-red " id="J_ActualFee">${shopCar.allMoney}</em></span>
						</div>

						<div id="holyshit268" class="pay-address">

							<p class="buy-footer-address">
								<span class="buy-line-title buy-line-title-type">寄送至：</span>
								<span class="buy--address-detail" id="send"></span>
							</p>
							<p class="buy-footer-address">
								<span class="buy-line-title">收货人：</span>
								<span class="buy-address-detail">
                                         <span class="buy-user" id="person">艾迪 </span>
									<span class="buy-phone" id="tel">15871145629</span></span>
							</p>
						</div>
					</div>


					<div id="holyshit269" class="submitOrder">
						<c:forEach items="${shopCar.shopCarItems}" var="shopCarItem">
						<form action="ShopController?action=pay&id=${shopCarItem.goodsInfo.id}" method="post" id="form">
							</c:forEach>
							<input type="hidden" name="o_sendtype" value="" id="o_sendtype"/>
							<input type="hidden" name="o_paytype" value="" id="o_paytype"/>
							<input type="hidden" name="o_paycount" value="${shopCar.allCount}" id="o_paycount"/>
							<input type="hidden" name="o_shperson" value="" id="o_shperson"/>
							<c:forEach items="${addresses}" var="addresse">
								<input type="hidden" name="userid" value="${addresse.userid}" id="userid"/>
								<input type="hidden" name="o_shphone" value="${addresse.phone}" id="o_shphone"/>
								<input type="hidden" name="o_shaddress" value="${addresse.address}" id="o_shaddress"/>
							</c:forEach>

							<input type="hidden" name="goodsnum" value="${shopCar.allCount}" id="goodsnum"/>
							<input type="hidden" name="goods_total_price" value="${shopCar.allMoney}" id="goods_total_price"/>
							<c:forEach items="${shopCar.shopCarItems}" var="shopCarItem">
							<input type="hidden" name="goodsid" value="${shopCarItem.goodsInfo.id}" id="goodsid"/>
							<input type="hidden" name="goodsname" value="${shopCarItem.goodsInfo.goods_name}" id="goodsname"/>
							<input type="hidden" name="goodsprice" value="${shopCarItem.goodsInfo.goods_price_off}" id="goodsprice"/>
							<input type="hidden" name="goods_description" value="${shopCarItem.goodsInfo.goods_description}" id="goods_description"/>
							<input type="hidden" name="goodspic" value="${shopCarItem.goodsInfo.goods_pic}" id="goodspic"/>
							</c:forEach>

								<div class="go-btn-wrap">
									<a id="go"  class="btn-go" tabindex="0" title="点击此按钮，提交订单">提交订单</a>
								</div>
						</form>

					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>


		<div class="clear"></div>
	</div>
</div>
<div class="footer">
	<div class="footer-hd">
		<p>
			<a href="#">恒望科技</a>
			<b>|</b>
			<a href="#">商城首页</a>
			<b>|</b>
			<a href="#">支付宝</a>
			<b>|</b>
			<a href="#">物流</a>
		</p>
	</div>

	<div class="footer-bd">
		<p>
			<a href="#">关于恒望</a>
			<a href="#">合作伙伴</a>
			<a href="#">联系我们</a>
			<a href="#">网站地图</a>
			<em>© 2015-2025 Hengwang.com 版权所有</em>
		</p>
	</div>
</div>
</div>
<div class="theme-popover-mask"></div>
<div class="theme-popover">

	<!--标题 -->
	<div class="am-cf am-padding">
		<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">新增地址</strong> / <small>Add address</small></div>
	</div>
	<hr/>

	<div class="am-u-md-12">
		<form class="am-form am-form-horizontal">

			<div class="am-form-group">
				<label for="user-name" class="am-form-label">收货人</label>
				<div class="am-form-content">
					<input type="text" id="user-name" placeholder="收货人">
				</div>
			</div>

			<div class="am-form-group">
				<label for="user-phone" class="am-form-label">手机号码</label>
				<div class="am-form-content">
					<input id="user-phone" placeholder="手机号必填" type="email">
				</div>
			</div>

			<div class="am-form-group">
				<label for="user-phone" class="am-form-label">所在地</label>
				<div class="am-form-content address">
					<select data-am-selected>
						<option value="a">浙江省</option>
						<option value="b">湖北省</option>
					</select>
					<select data-am-selected>
						<option value="a">温州市</option>
						<option value="b">武汉市</option>
					</select>
					<select data-am-selected>
						<option value="a">瑞安区</option>
						<option value="b">洪山区</option>
					</select>
				</div>
			</div>

			<div class="am-form-group">
				<label for="user-intro" class="am-form-label">详细地址</label>
				<div class="am-form-content">
					<textarea class="" rows="3" id="user-intro" placeholder="输入详细地址"></textarea>
					<small>100字以内写出你的详细地址...</small>
				</div>
			</div>

			<div class="am-form-group theme-poptit">
				<div class="am-u-sm-9 am-u-sm-push-3">
					<div class="am-btn am-btn-danger">保存</div>
					<div class="am-btn am-btn-danger close">取消</div>
				</div>
			</div>
		</form>
	</div>

</div>

<div class="clear"></div>
</body>

</html>