<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>JSTL Demo</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>用户名</th>
				<th>名称</th>
				<th>年龄</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.userName}</td>
					<td>${user.name}</td>
					<td>${user.age}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
