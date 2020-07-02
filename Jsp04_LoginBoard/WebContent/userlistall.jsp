<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="java.util.List" %>
<%@ page import="com.login.dto.MyMemberDto" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<MyMemberDto> list = (List<MyMemberDto>)request.getAttribute("list");
%>
<body>

	<h1>회원정보조회(all)</h1>
	<table border="1">
		<col width="50">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="500">
		<col width="150">
		<col width="300">
		<col width="100">
		<col width="50">
		<tr>
			<th>NO</th>
			<th>ID</th>
			<th>PW</th>
			<th>NAME</th>
			<th>ADDR</th>
			<th>PHONE</th>
			<th>EMAIL</th>
			<th>ENABLED</th>
			<th>ROLE</th>
		</tr>
<%
	for(MyMemberDto dto: list){
		
%>
	<tr>
		<td><%=dto.getMyno() %></td>
		<td><%=dto.getMyid() %></td>
		<td><%=dto.getMypw() %></td>
		<td><%=dto.getMyname() %></td>
		<td><%=dto.getMyaddr() %></td>
		<td><%=dto.getMyphone() %></td>
		<td><%=dto.getMyemail() %></td>
		<td><%=dto.getEnabled().equals("Y")?"가입":"탈퇴" %></td>
		<td><%=dto.getMyrole() %></td>
	</tr>
<%
	}
%>
		<tr>
		
			<td colspan="9">
				<button onclick="location.href='adminmain.jsp'">메인</button>
			</td> 
		</tr>
	</table>

</body>
</html>