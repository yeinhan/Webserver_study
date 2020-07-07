package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MyMVCBiz;
import com.mvc.biz.MyMVCBizImpl;
import com.mvc.dto.MyMVCDto;

@WebServlet("/MyMVCServlet")
public class MyMVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyMVCServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");

		MyMVCBiz biz = new MyMVCBizImpl();

		if (command.equals("list")) {
			List<MyMVCDto> list = biz.selectAll();
			
			request.setAttribute("list", list);
			dispatch("boardlist.jsp", request, response);
		}else if(command.equals("writeform")) {
			response.sendRedirect("boardwrite.jsp");
			
		}else if(command.equals("boardwrite")) {
			String writer=request.getParameter("writer");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			MyMVCDto dto = new MyMVCDto(writer,title,content);
			
			boolean res = biz.insert(dto);
			
			if(res) {
				jsResponse("글 작성 성공", "controller.do?command=list",response);
			}else {
				dispatch("controller.do?command=writeform", 
						request, response);
			}
			
		}else if(command.equals("detail")) {
			int seq= Integer.parseInt(request.getParameter("seq"));	//string타입
			
			MyMVCDto dto = biz.selectOne(seq);
			
			request.setAttribute("dto", dto);
			dispatch("boarddetail.jsp", request, response);
			
		}else if(command.equals("updateform")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				
				MyMVCDto dto = biz.selectOne(seq);
				request.setAttribute("dto", dto);
				dispatch("boardupdate.jsp", request, response);
		}else if(command.equals("boardupdate")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			MyMVCDto dto = new MyMVCDto(seq, title, content);
			boolean res = biz.update(dto);
			
			if(res) {
				jsResponse("글  수정 성공","controller.do?command=detail&seq="+seq	, response);
				
			}else {
				dispatch("controller.do?command=updateform&seq"+seq, request, response);
			}
		}
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String s = "<script type='text/javascript'>" + "alert('"+msg+"');" +
					"location.href='"+url+"';"+"</script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);

	}

}
