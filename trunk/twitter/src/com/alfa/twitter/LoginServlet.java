package com.alfa.twitter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String login = req.getParameter("login");
		String pass = req.getParameter("password");
		
		HttpSession session = req.getSession();
		
		session.setAttribute("login", login);
		session.setAttribute("password", pass);
		
		resp.sendRedirect("twitter");
	}

}
