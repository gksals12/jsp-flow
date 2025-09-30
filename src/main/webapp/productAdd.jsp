<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
</head>
<body>
	<form action="/flow/add-ok.product">
		<div>
			<span>상품이름</span>
			<input name="productName" />
		</div>
			<span>샹품가격</span>
			<input name="productPrice" />
		<div>
		</div>
		<div>
			<span>상품재고</span>
			<input name="productStock" />
		</div>
		<button>등록완료</button>
	</form>
</body>
</html>