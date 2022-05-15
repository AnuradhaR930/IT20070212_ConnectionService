 <%@page import = "org.electro_grid.model.Connections" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
        <script src="Components/jquery-3.2.1.min.js"></script>
        <script src="Components/main.js"></script>
        <script src="Components/connection.js"></script>
</head>
<body>
   <div class="container"><div class="row"><div class="col-6"> 
		<h1>Connection Management </h1>
		
		<form id="formConnection" name="formConnection">
		 Account No: 
		 <input id="accountNo" name="accountNo" type="text" class="form-control form-control-sm">
		 
		 <br> Connection Name: 
		 <input id="connectionName" name="connectionName" type="text"  class="form-control form-control-sm">
		
		 <br> Service Id: 
		 <input id="serviceId" name="serviceId" type="text" class="form-control form-control-sm">
		 
		 <br> Customer Id: 
		 <input id="customerId" name="customerId" type="text"  class="form-control form-control-sm">
		 <br>
		 
		 <br> Connection Date: 
		 <input id="connectionDate" name="connectionDate" type="text" class="form-control form-control-sm" readonly>
		 
		 <br> Connection Status 
		 <input id="connectionStatus" name="connectionStatus" type="text"  class="form-control form-control-sm" readonly>
		 <br>
		
		 <input id="btnSave" name="btnSave" type="button" value="Save"  class="btn btn-primary">
		 <input type="hidden" id="hidConnectionIDSave"  name="hidConnectionIDSave" value="">
		</form>
		
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divConnectionGrid">
		 <%
		 Connections itemObj = new Connections(); 
		 out.print(itemObj.readConnection()); 
		 %>
		</div>
		</div> </div> </div> 
</body>
</html>