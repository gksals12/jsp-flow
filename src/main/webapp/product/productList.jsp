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
   <table id="product-table">
      <tr>
         <th>선택</th>
         <th>상품번호</th>
         <th>상품명</th>
         <th>상품가격</th>
         <th>상품재고</th>
      </tr>
   </table>
   
   <hr />
   
   <form id="order-form" method="post" action="write-ok.order">
      <h2>주문 정보</h2>
      <table id="order-table">
         <tr>
            <th>상품명</th>
            <th>상품가격</th>
            <th>주문수량</th>
         </tr>
      </table>
      <button id="order-button" type="button" style="display:none;">주문하기</button>
   </form>
</body>
<script type="text/javascript">
   const orderForm = document.querySelector("#order-form");
   const products = JSON.parse(`${products}`);
   const productTable = document.querySelector("#product-table");
   
   products.forEach(({id, productName, productPrice, productStock}) => {
      productTable.innerHTML += (
         "<tr>" +
            "<td>" + "<input name='productId' type='checkbox' value='"+ id + "' />" + "</td>" +
            "<td>" + id + "</td>" +
            "<td>" + productName + "</td>" +
            "<td>" + productPrice + "</td>" +
            "<td>" + productStock + "</td>" +
         "</tr>"
      )
   })
   
   const orderTable = document.querySelector("#order-table") 
   const inputs = document.querySelectorAll("#product-table input");
   const orderButton = document.querySelector("#order-button");
   
   NodeList.prototype.find = Array.prototype.find;
   NodeList.prototype.filter = Array.prototype.filter;
   
   inputs.forEach((input) => {
      input.addEventListener("change", (e) => {
         const orderId = e.target.value
         const selectProduct = products.find((product) => product.id === Number(orderId))
         const {id, productName, productPrice, productStock} = selectProduct;
         const tr = document.createElement("tr")
         
         tr.innerHTML = (
            "<tr>" +
               "<input name='productId' type='hidden' value='"+ id + "' />" +
               "<td>" + productName + "</td>" +
               "<td>" + productPrice + "</td>" +
               "<td>" + "<input name='productCount' value='1' />" + "</td>" +
            "</tr>"
         )
         
         if(e.target.checked){
            /* 체크박스를 선택했다면 orderTable에 tr을 만들어서 추가 */
            orderTable.appendChild(tr);
            orderButton.style.display = "block";
            
         }else {
            /* 체크박스 해제되었을 때 해당 데이터를 삭제 */
            const trs = document.querySelectorAll("#order-table > tr")
            const selectTr = trs.find((tr) => tr.childNodes[0].value === orderId);
            orderTable.removeChild(selectTr);
            
            const removedTrs = document.querySelectorAll("#order-table > tr")
            orderButton.style.display = removedTrs.length === 0 ? "none" : "block"; 
         }
      })
   })
   
   orderButton.addEventListener("click", (e) => {
      e.preventDefault();
      
      const producIdInputs = document.querySelectorAll("#order-table input[name='productId']")
      const productCountInputs = document.querySelectorAll("#order-table input[name='productCount']")
      const submitState = [false, false]
      
      /* 주문 재고 수량이 0 */
      if(productCountInputs.filter((input) => Number(input.value) !== 0).length !== productCountInputs.length){
         return alert("주문 수량이 없습니다. 😅")
      }else {
         submitState[0] = true;
      }
      
      /* 가지고 있는 상품의 재고 수량보다 구매 수량이 많을 때 */
      productCountInputs.forEach((_, i) => {
         const productId = producIdInputs[i].value;
         const orderCount = productCountInputs[i].value;
         const orderedProduct = products.find(({id}) => String(id) === productId);
         
         if(orderCount > orderedProduct.productStock){
            return alert("구매수량이 상품 수량보다 많습니다.")
         }else {
            submitState[1] = true; 
         }
      })
      
      console.log(submitState.filter((state) => state).length === 2)
      if(submitState.filter((state) => state).length === 2){
         orderForm.submit()
      }
   })
   
</script>
</html>





