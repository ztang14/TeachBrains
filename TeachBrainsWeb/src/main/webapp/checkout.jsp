<%@page import="java.text.DecimalFormat"%>
<%@page import="entities.*" %>
<%@page import="java.util.*" %>
<%@page import="connection.DBCon" %>
<%@page import="Dao.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    List<Order> orders = null;
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    	orders = new OrderDao(DBCon.getConnection()).userOrders(auth.getId());
    }else{
    	response.sendRedirect("login.jsp");
    }
    Customer customer = (Customer) request.getSession().getAttribute("customer");
    CustomerDao cdao = new CustomerDao(DBCon.getConnection());
    UserDao udao = new UserDao(DBCon.getConnection());
    int id = udao.getUserId(auth.getUsername());
    customer = cdao.getCustomer(id);
    Card card = new Card();
    card = cdao.getCard(id);
    customer.setPayment(card);
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    double total = 0;
    if(cart_list != null){
    	ProductDao pDao = new ProductDao(DBCon.getConnection());
    	cartProduct = pDao.getCartProducts(cart_list);
    	total = pDao.getTotalCartPrice(cart_list);
    }
	request.setAttribute("cart_list",cart_list);
	request.setAttribute("total", total);
    %>
<!DOCTYPE html>
<html>
<head>
<title>Checkout Page</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
<style>
</style>
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
<div class="card w-100 mx-auto my-5">
<div class="card-header text-center">Order Information</div>
<div class="card-body">
<form id="CheckOutServlet" action="CheckOutServlet" method="post">
 <table class="table table-loght">
   <thead>
     <tr>
       <th scope="col">Product Name</th>
       <th scope="col">Category</th>
       <th scope="col">Price</th>
       <th scope="col">Quantity</th>
     </tr>
   </thead>
   <tbody>
   <% if(cart_list != null){
	   for(Cart c : cartProduct){%>
		   <tr>
		   <td><%= c.getName()%></td>
		   <td><%= c.getCategory() %></td>
		   <td><%= c.getPrice() %>$</td>
		   <td><%= c.getQuantity() %>   </td>
		   </tr>
	   
	    <%}  
	  }
     %>   
   </tbody>
</table>
  
   <table class="table table-loght">
   <thead>
     <tr>
       <th scope="col">FirstName</th>
       <th scope="col">LastName</th>
       <th scope="col">Phone Number</th>
       <th scope="col">shipping Address</th>
     </tr>
   </thead>
   <tbody>
      <tr>
		   <td><%= customer.getFirstName()%></td>
		   <td><%= customer.getLastName() %></td>
		   <td><%= customer.getPhone() %></td>
		   <td><%= customer.getAddress() %></td>
		   </tr> 
   </tbody>
</table>



<div class="card-header text-center">Payment Information</div>
<div class="card-body">
 
  
   <table class="table table-loght">
   <thead>
     <tr>
       <th scope="col">Card Number</th>
       <th scope="col">FirstName</th>
       <th scope="col">LastName</th>
       <th scope="col">Expired Date</th>
       <th scope="col">Bill Address</th>
     </tr>
   </thead>
   <tbody>
      <tr>
		   <td><%= customer.getPayment().getNumber() %></td>
		   <td><%= customer.getPayment().getFirstName() %></td>
		   <td><%= customer.getPayment().getLastName() %></td>
		   <td><%= customer.getPayment().getExpiredDate() %></td>
		   <td><%= customer.getPayment().getBillAddress() %></td>
		   </tr> 
   </tbody>
</table>
</div>
        
        
<div class="div-left">Total price: $ <%= total %></div>
<div class="form-group">
<label>Card Validation Code  :</label>
<input type="text" class="form-control" name="cvv" id="cvv" placeholder="cvv">
</div>
<div class="text-center">
<button style="padding: 10px 100px; margin:  10px  50px; border-radius: 30px;" type="submit" class="btn btn-primary">Check out</button>
<button style="padding: 10px 100px; margin:  10px  50px; border-radius: 30px;" type="reset" class="btn btn-primary">Cancel</button>
</div>
</form> 
</div>
</div>
</div>



<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>