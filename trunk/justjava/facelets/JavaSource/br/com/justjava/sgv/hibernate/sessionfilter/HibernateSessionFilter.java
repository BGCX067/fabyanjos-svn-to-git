/**
 *
 */
package br.com.justjava.sgv.hibernate.sessionfilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.justjava.sgv.hibernate.hibernateutil.HibernateUtil;

/**
 * @author wav138
 *
 */
public class HibernateSessionFilter implements Filter {
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain) throws ServletException {
		try {
			HibernateUtil.getCurrentSession().beginTransaction();//é obrigado estar
			filterChain.doFilter(servletRequest, servletResponse);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getCurrentSession().getTransaction().rollback();
			throw new ServletException(e);
		} finally {
			HibernateUtil.closeCurrentSession();
		}

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}