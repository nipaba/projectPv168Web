<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Land Manager</title>
</head>
<body>
<div class="top">
<h1>Land database</h1>
</div>

<div class="menu">&nbsp;</div>

<ul>
	<li><a href="/pv168WebApp">Back</a></li>
</ul>

<div class="data">
	<form action="/pv168WebApp/Land/Add" method="post">
		<ul>
			<li id="li_1"><label class="description" for="size">Size </label>
			<div><input type="text" name="size" /></div>
			</li>
			<li id="li_3"><label class="description" for="buildUpArea">Build-up area </label>
			<div><input type="text" name="buildUpArea" /></div>
			</li>
			<li id="li_2"><label class="description" for="catastralArea">Catastral area </label>
			<div><input type="text" name="catastralArea" /></div>
			</li>
			<li id="li_4"><label class="description" for="type">Type </label>
			<div><input type="text" name="type" /></div>
			</li>
			<li id="li_5"><label class="description" for="notes">Notes </label>
			<div><input type="text" name="notes" /></div>
			</li>
		</ul>
		<input type="submit" value="ADD" />&nbsp;
	</form>

<p>Database</p>


<table>
	<tbody>
		<tr>
			<th>Land ID</th>
			<th>Size</th>
			<th>Build-up area</th>
			<th>Catastral area</th>
			<th>Type</th>
			<th>Notes</th>
		</tr>
		<tr>
			<c:forEach items="${landList}" var="land"> 
				<tr>
					<td><c:out value="${land.landID }"></c:out></td>
					<td><c:out value="${land.size }"></c:out></td>
					<td><c:out value="${land.buildUpArea }"></c:out></td>
					<td><c:out value="${land.catastralArea }"></c:out></td>
					<td><c:out value="${land.type }"></c:out></td>
					<td><c:out value="${land.notes }"></c:out></td>
					<td>
					<form action="/pv168WebApp/Land/Remove" method="post"><input name="removeId" type="hidden" value="${land.landID }" /> <input type="submit" value="Remove" />&nbsp;</form>
					</td>
				</tr>
			</c:forEach>
		</tr>
	</tbody>
</table>
</div>
</body>
</html>
