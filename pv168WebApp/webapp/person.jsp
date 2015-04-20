<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="top">
		<h1>Catastral evidence of united kingdom republic GRowae</h1>
	</div>
	<div class="menu"></div>
	<ul>
		<li><a href="/pv168WebApp">Main page</a></li>
		<li><a href="/pv168WebApp/Person">Person Administration</a></li>
		<li><a href="/pv168WebApp/Land">Land Administration</a></li>
		<li><a href="/pv168WebApp/Ownership">Ownership Administration</a></li>
	</ul>

	<div class="data">
		<h2>Person Administration</h2>

		<form action="/pv168WebApp/Person/Add" method="post">
			<table>
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>BirthDate</th>
					<th>birthNumber</th>
					<th>State</th>
				<tr>
				<tr>
					<td><input type="text" name="name"></td>
					<td><input type="text" name="surname"></td>
					<td><input type="text" name="birthDate"></td>
					<td><input type="text" name="birthNumber"></td>
					<td><input type="text" name="state"></td>
				</tr>
			</table>
			<input type="submit" value="ADD">
		</form>
		<p>List of existing persons</p>

		<table>
			<tr>
				<th>Person ID</th>
				<th>Name</th>
				<th>Surname</th>
				<th>BirthDate</th>
				<th>birthNumber</th>
				<th>State</th>
			<tr>
				<c:forEach items="${personList}" var="person">
					<tr>
						<td><c:out value="${person.personId }"></c:out></td>
						<td><c:out value="${person.name }"></c:out></td>
						<td><c:out value="${person.surname }"></c:out></td>
						<td><c:out value="${person.birthDate }"></c:out></td>
						<td><c:out value="${person.birthNumber }"></c:out></td>
						<td><c:out value="${person.state }"></c:out></td>
						<td>
							<form action="/pv168WebApp/Person/Remove" method="post">
								<input type="hidden" name="removeId" value="${person.personId }">
								<input type="submit" value="Remove">
							</form>
					</tr>
				</c:forEach>
		</table>
	</div>
	<div class="footer"></div>



</body>
</html>