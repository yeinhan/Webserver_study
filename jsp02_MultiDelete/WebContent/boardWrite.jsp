<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("form").submit(function(){
			if($('form input:text').val()==""|| $('textarea').val() == ""){
				alert("모두 입력해 주세요");
				return false;
			}
		})
	})
</script>
</head>
<body>
	<%@ include file="./form/header.jsp" %>
	
	<form action="boardWrite_res.jsp" method="post">
		<table border="1">
			<col width="100px">
			<col width="400px">
			<tr>
				<th>WRITER</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>TITLE</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>CONTENT</th>
				<td><textarea rows="10" cols="60" name="content"></textarea></td>
	 		</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록">
					<input type="button" value="취소" onclick="location.href='boardlist.jsp'"> 
				</td>
			</tr>
		
		</table>
		
	</form>
	
	
	
	<%@ include file="./form/footer.jsp" %>
</body>
</html>