<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.login.dao.MyMemberDao" %>
<%@ page import="com.login.dto.MyMemberDto" %>
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
	System.out.println("[command: "+command+" ]");
	
	MyMemberDao dao = new MyMemberDao();
	
	if(command.equals("login")){
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MyMemberDto dto = dao.login(id,pw);
				
		if(dto.getMyid() != null ){
			session.setAttribute("dto", dto);
			session.setMaxInactiveInterval(60*60);
		
		/*
		스코프( scope ) 영역
		1.page 영역 : pageContext 객체
			JSP 파일에는 pageContext객체가 내장, 이 객체는 page 영역에서만 유효
		2.request 영역 : request 객체
			요청을 받아서 응답하기 까지 객체가 유효 , forward, include 사용하면 request 객체가 공유
		3.session 영역 : session 객체
			브라우저당 1개
			request.getSession()
		4.application 영역 : application 객체
			애플리케이션당 1나
			request.getServletContext() 메서드사용하여 애플리케션 객체를 얻을 수 있다.
		
		*/	
			if(dto.getMyrole().equals("ADMIN")){
				response.sendRedirect("adminmain.jsp");
				
				
			}else if(dto.getMyrole().equals("USER")){
				response.sendRedirect("usermain.jsp");
			}
			
			
		}else{	//로그인 실패
			
		
%>

	<script type="text/javascript">

	alert("login 실패");
	location.href="index.jsp"
</script>

<%

		}
		
	}else if(command.equals("logout")){
		session.invalidate();	//세선 정보 삭제
		response.sendRedirect("index.jsp");
		
	}else if(command.equals("userlistall")){
		List<MyMemberDto> list = dao.selectAll();
		request.setAttribute("list", list);
		pageContext.forward("userlistall.jsp");
		
	}else if(command.equals("userlistenabled")){
		List<MyMemberDto> list = dao.selectEnabled();
		request.setAttribute("list", list);
		pageContext.forward("userlistenabled.jsp");
		
	}else if(command.equals("updateroleform")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyMemberDto dto = dao.selectUser(myno);
		
		request.setAttribute("selectone", dto);
		pageContext.forward("updateroleform.jsp");
		
	}else if(command.equals("updaterole")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		String myrole= request.getParameter("myrole");
		
		int res = dao.updateRole(myno, myrole);
		if(res>0){
					
%>
	<script type="text/javascript">
		alert("등급 변경 성공");
		location.href="logincontroller.jsp?command=userlistenabled";
	</script>
<%
			
		} else{
			
%>	
	<script type="text/javascript">
		alert("등급 변경 실패");
		location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
	</script>	
<%
		}	
	
	}else if(command.equals("registform")){
		response.sendRedirect("registform.jsp");
		
	}else if(command.equals("idchk")){
		String myid=request.getParameter("id");
		String res = dao.idChk(myid);
		boolean idnotused = true;
		
		if(res!=null){	//db에 아이디가 존재한다.
			idnotused= false;
		
		}
		
		response.sendRedirect("idchk.jsp?idnotused=" + idnotused);
	}
	
	
	
	
%>




















</body>
</html>