<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ page import="com.el.score.Score" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% Score score = (Score) request.getAttribute("score"); %>
<body>
	<h1>El Page</h1>
	<table border="1">
		<tr>
			<th colspan="2">${score.name }님의 점수는....</th>
		</tr>
		<tr>
			<th>국어</th>
			<td><%=score.getKor() %></td>
		</tr>
		<tr>
			<th>영어</th>
			<td>${score.eng }</td>
		</tr>
		<tr>
			<th>math</th>
			<td>${score.math }</td>
			</tr>
		<tr>
			<th>avg</th>
			<td>${(score.kor+score.eng+score.math)/3 }</td>
		</tr>
		<tr>	
			<th>grade</th>
			<td>${score.grade }</td>
		</tr>	
	</table>
</body>
</html>