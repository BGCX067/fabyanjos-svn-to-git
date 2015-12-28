package com.alfa.twitter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.alfa.twitter.util.StringUtil;

@SuppressWarnings("serial")
public class SearchTwitterServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();

		int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
		req.setAttribute("page", Integer.valueOf(page));
		
		String login = (String) req.getSession().getAttribute("login");
		String pass = (String) req.getSession().getAttribute("password");

		try {
			Twitter twitter = new TwitterFactory().getInstance(login, pass);
			String q = (!StringUtil.isNull(req.getParameter("q"))) ? (req.getParameter("q")) : ((String)req.getAttribute("q"));
			
			req.setAttribute("q", q);
			
			Query query = new Query("q=" + q);
			query.setRpp(10);
			query.setPage(page);
			
			QueryResult result = twitter.search(query);
			List<Tweet> tweets = result.getTweets();

			req.setAttribute("tweets", tweets);
			req.getRequestDispatcher("/pages/search.jsp").forward(req,resp);  
		} catch (Exception e) {
			writer.println("Error: " + e.getMessage());
		}
	}
}
