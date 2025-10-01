<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문리스트</title>
</head>
<body>
	<h1>주문리스트</h1>
	<ul id="order-list-wrap">
	
	</ul>
</body>
<script type="text/javascript">
	const orderList = JSON.parse(`${orderList}`)
	console.log(orderList)
	const ul = document.querySelector("#order-list-wrap")
	orderList.forEach((order) =>{
		ul.innerHTML += (
			"<li>" + 
				"<a href='/flow/order/order_read.jsp?id=" + order.id + "' >" +
					"상품명: " + order.productName +
				"</a>" +
				"<p>" +
					"총금액: " + order.orderTotalPrice + 
				"</p>" +
				"<p>" + 
					"주문 수량: " + order.productCount +
				"</p>" +
				"<p>" +
					"주문자: " + order.memberName +
				"</p>" +
			"</li>"		
		)
	})
</script>
</html>