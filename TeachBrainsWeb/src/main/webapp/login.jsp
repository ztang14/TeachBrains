<%@page import="entities.*" %>
<%@page import="entities.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
      User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
    	response.sendRedirect("index.jsp");
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
    	request.setAttribute("cart_list",cart_list);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
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
<div class="card w-50 mx-auto my-5">
<div class="card-header text-center">User Login</div>
<div class="card-body">

<form action="LoginServlet" method="post">
<div class="form-group">
<label>Username</label>
<input type="text" class="form-control" name="user" id="user" placeholder="username">
</div>
<div class="form-group">
<label>Password</label>
<input type="password" class="form-control" name="pswd" id="pswd" placeholder="password">
</div>
<div class="text-center">
<button style="padding: 8px 30px; margin:  10px  50px; border-radius: 30px;" type="submit" class="btn btn-primary">Login</button>
<button style="padding: 8px 30px; margin:  10px  50px; border-radius: 30px;" type="reset" class="btn btn-primary">Cancel</button>
</div>
<p class="login-register-text">Don't have an account?<a href="register.jsp">  Register Here</a>
</form>

<!-- <form style="transition: transform 1s;" id="RegForm" action="Register" method="post">
<div class="form-group">
<label>Username</label>
<input type="text" class="form-control" name="user" id="user" placeholder="username">
</div>
<div class="form-group">
<label>Email</label>
<input type="email" class="form-control" name="email" id="email" placeholder="email">
</div>
<div class="form-group">
<label>Password</label>
<input type="password" class="form-control" name="pswd" id="pswd" placeholder="password">
</div>
<div class="form-group">
<label>Confirm Password</label>
<input type="password" class="form-control" name="cfpswd" id="cfpswd" placeholder="cfpassword">
</div>
<div class="text-center">
<button style="padding: 8px 30px; margin:  10px  50px; border-radius: 30px;" type="submit" class="btn btn-primary">Register</button>
<button style="padding: 8px 30px; margin:  10px  50px; border-radius: 30px;" type="reset" class="btn btn-primary">Cancel</button>
</div> -->

<!-- </form> -->
</div>
</div>
</div>    
    
    
    
    

 
   <script>
 
 
 var LoginForm = document.getElementById("LoginForm");
 var RegForm = document.getElementById("RegForm");
 var Indicator = document.getElementById("Indicator");
 
 function register(){
	 RegForm.style.transform = "translateX(0px)";
	 LoginForm.style.transform = "translateX(0px)";
	 Indicator.style.transform = "translateX(100px)";
 }
 
 function login(){
	 RegForm.style.transform = "translateX(300px)";
	 LoginForm.style.transform = "translateX(300px)";
	 Indicator.style.transform = "translateX(0px)";
 }
 
 </script>
 
 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>