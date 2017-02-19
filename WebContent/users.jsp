<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management</title>
<style>
	<%@ include file="css/bootstrap.min.css" %>
</style>

<script>
	function radioSubmit() {
		document.getElementById("radioForm").submit();
	}
</script>

</head>
<body>
<div class="container">
	<h1>User Management</h1>
	<br/>

	<a href="UserController?page=user" class="btn btn-primary" >Create User</a>
	<br/> <br/>
	
	<form method="get" action="UserController" class="from-group form-inline" >
		<input type="text" name="search"  class="form-control"/>
		<button type="submit"  class="btn btn-info">Search</button>
	</form>
	 <br/>
	<label><c:out value="${message }"/></label>
	<br/>
	

	
	<form id="radioForm" method="get" action="UserController" class="row container form-inline">

		<div class="input-group">
			<label class="radio-inline form-control">
				<input type="radio" class="input-group-addon" value="family" <c:if test="${radio == 1}"> checked </c:if>  name="filter" onChange="radioSubmit();">
				Last Name</label>
		</div>
		<div class="input-group">
			<label class="radio-inline form-control">
				<input type="radio" class="input-group-addon" value="dob" <c:if test="${radio == 2}"> checked </c:if> name="filter" onChange="radioSubmit();">
			    Date of birth</label>
		</div>
	</form>
	
	<table class="table table-bordered">
		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Date of birth</th>
			<th>PhoneNumber</th>
			<th>Email</th>
			<th colspan=2>Options</th>
		</tr>
		<c:forEach items="${allUsersList}" var="user">
			<tr>
				<td><c:out value="${user.userId}" /></td>
				<td><c:out value="${user.firstName}"/></td>
				<td><c:out value="${user.lastName}"/></td>
				<td><c:out value="${user.dateOfBirth}"/></td>
				<td><c:out value="${user.phoneNumber}"/></td>
				<td><c:out value="${user.email}"/></td>
				<td><a href="UserController?page=user&edit=<c:out value="${user.userId}"/>">Edit</a></td>
				<td><a href="UserController?delete=<c:out value="${user.userId}"/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

</div>
</body>
</html>