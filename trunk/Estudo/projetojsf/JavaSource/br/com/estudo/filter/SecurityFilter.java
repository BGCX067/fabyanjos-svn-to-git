package br.com.estudo.filter;

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

import br.com.estudo.dto.UsuarioDTO;

public class SecurityFilter implements Filter {

	ServletContext context;

	public void destroy() {
		context = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String path = httpRequest.getRequestURI();
		UsuarioDTO user = (UsuarioDTO) httpRequest.getSession().getAttribute(
				"user");
		if (path.contains("pages") && user == null) {
			if (!path.contains("cadastrar.usuario") && !path.contains("login")) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/pages/login.jsf");
				httpRequest.getSession().setAttribute("notLogInUser", "notLogInUser");
			}
		}
		chain.doFilter(httpRequest, httpResponse);
	}

	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();
	}
}
