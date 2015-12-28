package com.alfa.twitter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@SuppressWarnings("serial")
public class NewStatusServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		
		String login = (String) req.getSession().getAttribute("login");
		String pass = (String) req.getSession().getAttribute("password");
		
		String status = req.getParameter("status");

		try {
			Twitter twitter = new TwitterFactory().getInstance(login, pass);
			twitter.updateStatus(status);
			
			resp.sendRedirect("twitter");
		} catch (Exception e) {
			writer.println("Error: " + e.getMessage());
		}
	}
}
