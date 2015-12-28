package com.alfa.twitter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@SuppressWarnings("serial")
public class TwitterServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();

		int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
		req.setAttribute("page", Integer.valueOf(page));
		
		String login = (String) req.getSession().getAttribute("login");
		String pass = (String) req.getSession().getAttribute("password");

		try {
			Twitter twitter = new TwitterFactory().getInstance(login, pass);
			// requesting page 1, number of elements per page is 10
			Paging paging = new Paging(page, 10);
			ResponseList<Status> statuses = twitter.getFriendsTimeline(paging);

			req.setAttribute("status", statuses);
			req.getRequestDispatcher("/pages/home.jsp").forward(req,resp);  
		} catch (Exception e) {
			writer.println("Error: " + e.getMessage());
			resp.sendRedirect("twitter");
		}
	}
}
