<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% request.setCharacterEncoding("UTF-8"); %>
 <% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.multi.dto.MDBoardDto" %>
<%@ page import="com.multi.dao.MDBoardDao" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	MDBoardDao dao = new MDBoardDao();
	List<MDBoardDto> list = dao.selectAll();
%>


</head>
<body>
<%@ include file="./form/header.jsp" %>

	<h1>L I S T</h1>
	<form action="">
		<table border="1">
			<col width="30px">
			<col width="50px">
			<col width="100px">
			<col width="300px">
			<col width="100px">
			<tr>
				<th><input type="checkbox" name="all"></th>
				<th>NO</th>
				<th>WRITER</th>
				<th>TITLE</th>
				<th>DATE</th>
			</tr>
			
			<%
				if(list.size()==0){
			%>
			<tr>
				<td colspan="5">------글이 존재하지 않습니다</td>
			</tr>
			<%
				}else{
					for(MDBoardDto dto:list){
			%>
			
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getSeq() %>"></td>
				<td><%=dto.getSeq() %></td>
				<td><%=dto.getWriter() %></td>
				<td><a href="boarddetail.jsp?seq=<%=dto.getSeq()%>" ><%=dto.getTitle() %></td>
				<td><%=dto.getRegdate() %></td>
			</tr>
			<%
					}
				}
			%>
			
			<tr>
				<td colspan="5">
					<input type="submit" value="삭제">
					<input type="button" onclick="location.href='boardWrite.jsp'" value="글쓰기">
				</td>
			</tr>
		</table>
	</form>


<%@ include file="./form/footer.jsp" %>

</body>
</html>