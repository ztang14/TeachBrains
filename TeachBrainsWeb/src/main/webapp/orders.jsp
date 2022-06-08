<%@page import="java.text.DecimalFormat"%>
<%@page import="entities.*" %>
<%@page import="entities.*" %>
<%@page import="java.util.*" %>
<%@page import="connection.DBCon" %>
<%@page import="Dao.OrderDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    List<Order> orders = null;
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    	orders = new OrderDao(DBCon.getConnection()).userOrders(auth.getId());
    }else{
    	response.sendRedirect("login.jsp");
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
    	request.setAttribute("cart_list",cart_list);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Orders Page</title>
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
  <div class="card-header my-3">All Orders</div>
<table class="table table-light">
   <thead>
     <tr>
       <th scope="col">Date</th>
       <th scope="col">Name</th>
       <th scope="col">Category</th>
       <th scope="col">Quantity</th>
       <th scope="col">Price</th>
       <th scope="col">Cancel</th>
     </tr>
   </thead>
   <tbody>
   <%
     if(orders != null){
    	 for(Order o : orders){%>
    	 <tr>
    	 <td><%= o.getDate() %></td>
    	 <td><%= o.getName() %></td>
    	 <td><%= o.getCategory() %></td>
    	 <td><%= o.getQuantity() %></td>
    	 <td><%= o.getPrice() %></td>
    	 <td><a class="btn btn-sm btn-danger" href="OrderCancelServlet?id=<%= o.getOrderId() %>">Cancel</a></td>
    	 </tr>
    	 <%}
     }
   %>
   </tbody>
   </table>
   </div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>