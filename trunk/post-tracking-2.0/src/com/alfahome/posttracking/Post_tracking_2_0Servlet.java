package com.alfahome.posttracking;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Post_tracking_2_0Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
