<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<style>
	<%@ include file="css/bootstrap.min.css" %>
</style>
</head>
<body>
<div class="container">
	<h1>Create User</h1>
	<br/>
	<form action="UserController" method="post">
		<input type="hidden" class="form-control" readonly="readonly" name="userid" value="<c:out value="${user.userId}" />" />
   	   	<div class="container row form-group" >
			<input type="text" class="form-control" name="firstname" placeholder="FirstName" value="<c:out value="${user.firstName}"/>" required/>
		</div>
		<div class="container row form-group" >
			<input type="text" class="form-control" name="lastname" placeholder="LastName" value="<c:out value="${user.lastName}"/>" required/>
		</div>
		<div class="container row form-group" >			
			<input type="date" class="form-control" name="dateofbirth" placeholder="Date of birth" value="<c:out value="${user.dateOfBirth}"/>" required/>
		</div>
		<div class="container row form-group" >
			<input type="tel" class="form-control" name="phonenumber" placeholder="Phone number" value="<c:out value="${user.phoneNumber}"/>" required/>
		</div>
		<div class="container row form-group" >
			<input type="email" class="form-control" name="email" placeholder="Email" value="<c:out value="${user.email}"/>" required/>
		</div>
		<div class="container row form-inline" >			
			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="/UserManagement/UserController?page=index" class="btn btn-primary">Back</a>
		</div>
	</form>
</div>
</body>
</html>