<%@page import="java.text.DecimalFormat"%>
<%@page import="entities.*" %>
<%@page import="java.util.*" %>
<%@page import="connection.DBCon" %>
<%@page import="Dao.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%   
    
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    }
    
    ProductDao pd = new ProductDao(DBCon.getConnection());
    List<Product> products = pd.getAllProducts();
      
    CustomerDao cd = new CustomerDao(DBCon.getConnection());
    List<Customer> customers = cd.getAllCustomer();
    
    OrderDao od = new OrderDao(DBCon.getConnection());
    List<Order> orders = od.getAllOrders();
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
<li class="nav-item"><a class="nav-link" href="Admin.jsp">My Admin<span class="badge badge-danger px-1">${cart_list.size() }</span></a></li>
      <li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
      <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
   
    </ul>
  </div>
  </div>
</nav>

<div class="container">
  <div class="card-header my-3">ADMIN</div>
    <div class="row">
            <div class="card ">
                <div class="card-header">
                    <div class="bg-white shadow-sm pt-4 pl-2 pr-2 pb-2">
                        <!-- Credit card form tabs -->
                        <ul role="tablist" class="nav bg-light nav-pills rounded nav-fill mb-3">
                            <li class="nav-item"> <a data-toggle="pill" href="#addProduct" class="nav-link active "> <i class="fas fa-credit-card mr-2"></i> Add Product </a> </li>
                            <li class="nav-item"> <a data-toggle="pill" href="#products" class="nav-link "> <i class="fab fa-paypal mr-2"></i> Product </a> </li>
                            <li class="nav-item"> <a data-toggle="pill" href="#customers" class="nav-link "> <i class="fas fa-mobile-alt mr-2"></i> Customers </a> </li>
                            <li class="nav-item"> <a data-toggle="pill" href="#orders" class="nav-link "> <i class="fas fa-mobile-alt mr-2"></i> Orders </a> </li>
                        </ul>
                    </div> <!-- End -->
                    <!-- Credit card form content -->
                    <div class="tab-content">
                        <!-- credit card info-->
                        <div id="addProduct" class="tab-pane fade show active pt-3">
                           <form id="AddProductServlet" action="AddProductServlet" method="post">
                                <div class="form-group"> <label for="username">
                                        <h6>Product Name</h6>
                                    </label> <input type="text" name="name" placeholder="Product Name" required class="form-control "> </div>
                                <div class="form-group"> <label for="cardNumber">
                                        <h6>Product Category</h6>
                                    </label>
                                    <div class="input-group"> <input type="text" name="category" placeholder="Product Category" class="form-control " required>
                                        <div class="input-group-append"> <span class="input-group-text text-muted"> <i class="fab fa-cc-visa mx-1"></i> <i class="fab fa-cc-mastercard mx-1"></i> <i class="fab fa-cc-amex mx-1"></i> </span> </div>
                                    </div>
                                </div>
                                <div class="form-group"> <label for="username">
                                        <h6>Product Img</h6>
                                    </label> <input type="text" name="img" placeholder="Product Img" required class="form-control "> </div>
                                <div class="row">
                                    <div class="col-sm-8">
                                        <div class="form-group"> <label><span class="hidden-xs">
                                                    <h6>Price</h6>
                                                </span></label>
                                            <div class="input-group"> <input type="number" placeholder="Price" name="price" class="form-control" required>  </div>
                                        </div>
                                    </div>
                           
                                </div>
                                <div class="card-footer"> <button type="submit" class="subscribe btn btn-primary btn-block shadow-sm"> Add Product </button>
                            </form>
                        </div>
                    </div> 
                    
                    
                    
<div id="products" class="tab-pane fade pt-3">
 <table class="table table-light">
   <thead>
     <tr>
       <th scope="col">Id</th>
       <th scope="col">Name</th>
       <th scope="col">Category</th>
       <th scope="col">Price</th>
     </tr>
   </thead>
   <tbody>
   <%
   if(!products.isEmpty()){
 	  for(Product p : products){%>
    	 <tr>
    	 <td><%= p.getId() %></td>
    	 <td><%= p.getName() %></td>
    	 <td><%= p.getCategory() %></td>
    	 <td><%= p.getPrice() %></td>
    	 </tr>
    	 <%}
     }
   %>
   </tbody>
   </table>
 </div>
  <div id="customers" class="tab-pane fade pt-3">
                                                     <table class="table table-light">
   <thead>
     <tr>
       <th scope="col">Id</th>
       <th scope="col">FirstName</th>
       <th scope="col">LastName</th>
       <th scope="col">Phone</th>
       <th scope="col">Address</th>
     </tr>
   </thead>
   <tbody>
   <%
   if(!customers.isEmpty()){
 	  for(Customer c : customers){%>
    	 <tr>
    	 <td><%= c.getId() %></td>
    	 <td><%= c.getFirstName() %></td>
    	 <td><%= c.getLastName() %></td>
    	 <td><%= c.getPhone() %></td>
    	 <td><%= c.getAddress() %></td>
    	 </tr>
    	 <%}
     }
   %>
   </tbody>
   </table>
</div>
          
    <div id="orders" class="tab-pane fade pt-3">
                                                     <table class="table table-light">
   <thead>
     <tr>
       <th scope="col">Order Id</th>
       <th scope="col">Product Id</th>
       <th scope="col">User Id</th>
       <th scope="col">Quantity</th>
       <th scope="col">Date</th>
     </tr>
   </thead>
   <tbody>
   <%
   if(!orders.isEmpty()){
 	  for(Order o : orders){%>
    	 <tr>
    	 <td><%= o.getOrderId() %></td>
    	 <td><%= o.getId() %></td>
    	 <td><%= o.getUid() %></td>
    	 <td><%= o.getQuantity() %></td>
    	 <td><%= o.getDate() %></td>
    	 </tr>
    	 <%}
     }
   %>
   </tbody>
   </table>
                </div>        
          
          
            </div>
        </div>
    </div>
</div>

    
    
    
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>