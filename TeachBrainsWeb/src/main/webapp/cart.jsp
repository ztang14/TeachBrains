<%@page import="java.text.DecimalFormat"%>
<%@page import="entities.*" %>
<%@page import="java.util.*" %>
<%@page import="connection.DBCon" %>
<%@page import="Dao.ProductDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
      DecimalFormat dcf = new DecimalFormat("#.##");
      request.setAttribute("dcf", dcf);
      User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if(cart_list != null){
    	ProductDao pDao = new ProductDao(DBCon.getConnection());
    	cartProduct = pDao.getCartProducts(cart_list);
    	double total = pDao.getTotalCartPrice(cart_list);
    	request.setAttribute("cart_list",cart_list);
    	request.setAttribute("total", total);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Cart Page</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/brands.min.css"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<style type="text/css">
.table tbody td{
   vartical-align:middle;
}

.btn-incre, .btn-decre{
   box-shodow:none;
   font-size: 25px;
}
</style>
</head>
<body>
 <nav class="navbar navbar-expand-lg navbar-light bg-light">
<div class="container">
  <a class="navbar-brand" href="index.jsp">OnlineShop</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
       <a class="nav-link" href="index.jsp">Home </a></li>
      <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger px-1">${cart_list.size() }</span></a></li>
      <%
      if(auth != null){%>
      <li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
            <li class="nav-item"><a class="nav-link" href="customers.jsp">Account</a></li>
      <li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
      <%}else{ %>
      <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
      <%} 
      %>
    </ul>
  </div>
  </div>
</nav>


<div class="container">
  <div class="d-flex py-3">
  <h3>Total price: $ ${(total>0)?dcf.format(total):0}</h3>
  <a class="mx-3 btn btn-primary" href="CheckOutServlet">check out</a></div>
</div> 
<table class="table table-loght">
   <thead>
     <tr>
       <th scope="col">Name</th>
       <th scope="col">Category</th>
       <th scope="col">Price</th>
       <th scope="col">Buy Now</th>
       <th scope="col">Cancel</th>
     </tr>
   </thead>
   <tbody>
   <% if(cart_list != null){
	   for(Cart c : cartProduct){%>
		   <tr>
		   <td><%= c.getName()%></td>
		   <td><%= c.getCategory() %></td>
		   <td><%= c.getPrice() %>$</td>
		   <td>
		   <form action="OrderNowServlet" method="post" class="form-inline">
		     <input type="hidden" name="id" value="<%= c.getId() %>" class="form-input">
		     <div class="form-group d-flex justify-content-between w-50">
		        <a class="btn btn-sm btn-incre" href="QuantityIncDecServlet?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
		        <input type="text" name="quantity" class="form-control w-50" value="<%= c.getQuantity()%>" readonly>
		        <a class="btn btn-sm btn-decre" href="QuantityIncDecServlet?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
		   </div>
		   <button type="submit" class="btn btn-primary btn-sm">Buy</button>
		   </form>
		   </td>
		   <td><a class="btn btn-sm btn-danger" href="RemoveServlet?id=<%= c.getId() %>">Remove</a></td>
		   </tr>
	   
	    <%}  
	  }
     %>
   
   </tbody>
</table>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>


</body>
</html>