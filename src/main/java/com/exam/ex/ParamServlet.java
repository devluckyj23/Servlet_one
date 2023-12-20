package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//"/param.do?x=포로리&y=너부리"로 요청을 보내면,
//화면에
//"x 파라미터값 : 포로리"
//"y 파라미터값 : 너부리"
//라고 출력되도록 ParamServlet을 완성하세요.

@WebServlet("/param.do")
public class ParamServlet  extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//GET 방식으로 전송된 요청파라미터값을 읽을 때 사용할 문자 인코딩 방식은
		//톰캣의 sever.xml 파일에 있는 <Connector port="8080" protocol="HTTP/1.1"> 태그에  
		//URIEncoding="UTF-8" 속겅을 추가하여 지정 가능
		
		
		//POST 방식으로 전송된 요청파라미터값을 읽을 때 사용할 문자인코딩 방식 설정
		req.setCharacterEncoding("UTF-8");
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOCTYPE html>             ");
		out.println("<html>                      "); 
		out.println("<head>                      ");
		out.println("<meta charset=\"UTF-8\">    ");
		out.println("<title>포로리와 너부리</title>        ");
		out.println("<style>");
		out.println("h1 {margin: 100px; ");	
		out.println("font-family: Georgia, 'Times New Roman', Times, serif;");	
		out.println("color: rgb(78, 61, 139);");
		out.println("font-size: 100px;}");
		out.println("</style>");
		out.println("</head>                     ");
		out.println("<body>                      ");
		
		String x = req.getParameter("x");
		String y = req.getParameter("y");
		
		out.println("	<h1>포로리 : "+ x + "</h1>");
		out.println("	<h1>너부리 : "+ y + "</h1>");		
		out.println("</body>                     ");
		out.println("</html>                     ");
		
	}
}


