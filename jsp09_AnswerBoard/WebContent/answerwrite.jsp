<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Answer Write</h1>
<form action="answer.do" method="post">
	<input type="hidden" name="command" value="answerwrite">
	 <input type="hidden" name="parentboardno" value="${parent.boardno }">
	<table border="1">
		<tr>
			<td>WRITER</td>
			<td><input type="text" name="writer"></td>
			
		</tr>
		<tr>
			<th>TITLE</th>
			<td><input type="text" name="title" value="RE:${parent.title }"></td>
		</tr>
		<tr>
			<th>CONTENT</th>
			<td><textarea rows="10" cols="60" name="content"></textarea>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="확인">
				<input type="button" value="취소" onclick="location.href='answer.do?command=detail&boardno=${parent.boardno}'"> 
			</td>
		</tr>
	</table>
</form>






</body>
</html>