package br.com.sphera.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sphera.entities.Usuario;

public class AuthenticationFilter implements Filter {

	private ServletContext context;

	@Override
	public void destroy() {
		this.context = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String path = request.getRequestURI();
		Usuario user = (Usuario) request.getSession().getAttribute("user");
	
		if (path.contains("app") && user == null) {
			if (!path.contains("login")) {
				response.sendRedirect(request.getContextPath() + "/app/login/login.htm");
				request.getSession().setAttribute("notLogInUser", "notLogInUser");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.context = config.getServletContext();
	}

}
