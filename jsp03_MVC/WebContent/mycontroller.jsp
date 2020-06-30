<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.mvc.dto.MVCBoardDto" %>
<%@ page import="com.mvc.dao.MVCBoardDao" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("[command:"+command+"]");
	
	MVCBoardDao dao = new MVCBoardDao();
	
	if(command.equals("boardlist")){
		List<MVCBoardDto> list = dao.selectAll();
		
		request.setAttribute("allList", list);
		pageContext.forward("boardlist.jsp");
		
		/*
			페이지 효율적 제어
			-포워드: req,res가 유지
				1)requestDispatcher 이용
				2)<jsp: forward>
				3)pageContext, forward()
			-리다이렉트:새로운 request, reponse
				response.sendRedirect()
		*/
	} else if(command.equals("boarddetail")){
		//1.파라미터로 넣어오는 seq처리
		//2.dao를 이용하여 data처리
		//3.페이지 처리(boarddetail.jsp)
%>

<%
	}
%>




</body>
</html>