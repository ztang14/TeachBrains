<%@page import="java.text.DecimalFormat"%>
<%@page import="entities.*" %>
<%@page import="entities.*" %>
<%@page import="java.util.*" %>
<%@page import="connection.DBCon" %>
<%@page import="Dao.OrderDao" %>
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
    %>
<!DOCTYPE html>
<html>
<head>
<title>Customers Page</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
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
<div class="card w-100 mx-auto my-5">
<div class="card-header text-center">Account Information</div>
<div class="card-body">
<form id="CustomerUpdateServlet" action="CustomerUpdateServlet" method="post">
<div class="form-group">
<label>Username : <%=auth.getUsername() %></label>
<input type="text" class="form-control" name="user" id="user" placeholder="username">
</div>
<div class="form-group">
<label>Email : <%=auth.getEmail() %></label>
<input type="email" class="form-control" name="email" id="email" placeholder="email">
</div>
<div class="form-group">
<label>First Name</label>
<input type="text" class="form-control" name="fname" id="fname" placeholder="first name">
</div>
<div class="form-group">
<label>Last Name</label>
<input type="text" class="form-control" name="lname" id="lname" placeholder="last name">
</div>
<div class="form-group">
<label>Phone Number</label>
<input type="text" class="form-control" name="phone" id="phone" placeholder="phone number">
</div>
<div class="form-group">
<label>Address</label>
<input type="text" class="form-control" name="address" id="address" placeholder="address">
</div>
<div class="text-center">
<button style="padding: 10px 100px; margin:  10px  50px; border-radius: 30px;" type="submit" class="btn btn-primary">Save</button>
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
    