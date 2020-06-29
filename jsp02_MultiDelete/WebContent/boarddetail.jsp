<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% request.setCharacterEncoding("UTF-8"); %>
 <% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.multi.dto.MDBoardDto" %>
<%@ page import="com.multi.dao.MDBoardDao" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#updateform{display:none;}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	function updateformView(){
		$('#detailform').hide();
		$('#updateform').show();
	}
	function detailformView(){
		$('#detailform').show();
		$('#updateform').hide();
	}
</script>
</head>
<body>
<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	MDBoardDao dao = new MDBoardDao();
	MDBoardDto dto = dao.selectOne(seq);
	
%>

<%@ include file="./form/header.jsp" %>

	<div id="detailform">
		<h1>게시글 보기</h1>
		<table border="1">
			<tr>
				<th>NO</th>
				<td><%=dto.getSeq() %></td>
			</tr>
			<tr>
				<th>WRITER</th>
				<td><%=dto.getWriter() %></td>
			</tr>
			<tr>
				<th>DATE</th>
				<td><%=dto.getRegdate() %></td>
			</tr>
			<tr>
				<th>TITLE</th>
				<td><%=dto.getTitle() %></td>
			</tr>
			<tr>
				<th>CONTENT</th>
				<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent()  %></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="수정" onclick="updateformView();">
					<input type="button" value="삭제">
					<input type="button" value="목록" onclick="location.href='boardlist.jsp'">
				</td>
			</tr>
		</table>
	</div>
	
	<!-- ------------------------------------------------------------------------ -->
	
	<div id="updateform">
		<h1>게시글 수정</h1>
		
		<form action="boardupdate_res.jsp" method="post">
			<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
			
			<table border="1">
				<tr>
					<th>NO</th>
					<td><%=dto.getSeq() %></td>
				</tr>
				<tr>
					<th>WRITER</th>
					<td><%=dto.getWriter() %></td>
				</tr>
				<tr>
					<th>DATE</th>
					<td><%=dto.getRegdate() %></td>
				</tr>
				<tr>
					<th>TITLE</th>
					<td><input type="text" name="title" value="<%=dto.getTitle() %>"></td>
				</tr>
				<tr>
					<th>CONTENT</th>
					<td><textarea rows="10" cols="60" name="content"><%=dto.getContent() %></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정완료">
						<input type="button" value="취소" onclick="detailformView();">
						
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>