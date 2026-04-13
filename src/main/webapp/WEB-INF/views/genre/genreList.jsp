<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TMDB 장르</title>
<link rel="stylesheet" type="text/css" href="/css/hello-spring.css" />
</head>
<body>
	<h1>TMDB 장르</h1>
	<table class="grid">
		<thead>
			<tr>
				<th>카테고리 아이디</th>
				<th>장르 이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${result}" var="genre">
				<tr>
					<td>${genre.categoryId}</td>
					<td>${genre.categoryName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>