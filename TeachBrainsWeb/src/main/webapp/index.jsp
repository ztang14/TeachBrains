<%@page import="entities.*" %>
<%@page import="java.util.*" %>
<%@page import="connection.DBCon" %>
<%@page import="Dao.ProductDao" %>
<%@page import="entities.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    }
    %>
    <% 
   ProductDao pd = new ProductDao(DBCon.getConnection());
   List<Product> products = pd.getAllProducts();
   
   ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
   if(cart_list != null){
   	request.setAttribute("cart_list",cart_list);
   }
   %>
   
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
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
   <div class="card-header my-3">All Products</div>
   <div class="row">
   <%
      if(!products.isEmpty()){
    	  for(Product p : products){%>
    		     <div class="col-md-3 my-3">
    		       <div class="card w-100" style="width: 18rem;">
    		         <img class="card-img-top" src="./img/<%=p.getImage() %>" alt="Card image cap">
    		         <div class="card-body">
    		           <h5 class="card-title"><%=p.getName()%></h5>
    		           <h6 class="price">Price: $<%=p.getPrice() %></h6>
    		           <h6 class="category">Category: <%=p.getCategory() %></h6>
    		           <div class="mt-3 d-flex justify-content-between">
    		             <a href="AddToCartServlet?id=<%=p.getId()%>" class="btn btn-dark">Add to Cart</a>
    		             <a href="OrderNowServlet?quantity=1&id=<%=p.getId() %>" class="btn btn-primary">Buy Now</a>
    		           </div>
    		         </div>
    		     </div>
    		   </div>
    	  <%}
      }   
 %> 
  

 </div>
 </div>

 
 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>