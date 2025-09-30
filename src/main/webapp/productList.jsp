<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리스트</title>
</head>
<body>
	<table>
		<tr>
			<th>상품번호</th>
			<th>상품이름</th>
			<th>상품가격</th>
			<th>상품재고</th>
		</tr>
		<c:forEach var="product" items="${products}">
			<tr>
				<td><c:out value="${product.getId()}"></c:out></td>
				<td><c:out value="${product.getProductName()}"></c:out></td>
				<td><c:out value="${product.getProductPrice()}"></c:out></td>
				<td><c:out value="${product.getProductStock()}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
<script type="text/javascript">
	const products = JSON.parse(`${productsJSON}`)
	const table = document.querySelector("table")
	console.log(products)
	console.log(table)
	
 	products.forEach((product) => {
		console.log(product.id)
		table.innerHTML += (
 			"<tr>" +
 				"<td>" + product.id + "</td>" +
 				"<td>" + product.productName + "</td>" +
 				"<td>" + product.productPrice + "</td>" +
 				"<td>" + product.productStock + "</td>" +
 			"</tr>"
 		)
	}) 
</script>

</html>