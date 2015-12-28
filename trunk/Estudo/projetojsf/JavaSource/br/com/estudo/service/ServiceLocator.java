package br.com.estudo.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.estudo.exception.ServiceLocatorException;

public class ServiceLocator implements Serializable {
	private static final long serialVersionUID = -1492281111465596234L;

	private static ServiceLocator serviceLocator = null;
	private InitialContext context;
	private Map<String, Object> cache;

	private static final String JNDI_CONFIG_FILE = "/jndi.properties";

	private static final Logger log = Logger.getLogger(ServiceLocator.class
			.getName());

	private ServiceLocator() throws ServiceLocatorException {
		try {
			cache = Collections.synchronizedMap(new HashMap<String, Object>());
			InputStream is = getClass().getResourceAsStream(JNDI_CONFIG_FILE);
			Properties properties = new Properties();
			try {
				properties.load(is);
				context = new InitialContext(properties);
			} catch (IOException e) {
				log.log(Level.WARNING,
						"Não foi possível localizar o arquivo de configurações "
								+ JNDI_CONFIG_FILE, e);
				context = new InitialContext();
			} catch (NullPointerException e) {
				log.log(Level.WARNING,
						"Não foi possível localizar o arquivo de configurações "
								+ JNDI_CONFIG_FILE, e);
				context = new InitialContext();
			}

		} catch (NamingException ex) {
			log.log(Level.SEVERE, ex.getMessage(), ex);
			throw new ServiceLocatorException(ex);
		}
	}

	public static synchronized ServiceLocator getInstance()
			throws ServiceLocatorException {
		if (serviceLocator == null) {
			serviceLocator = new ServiceLocator();
		}
		return serviceLocator;
	}

	public Object getHome(String jndiName) throws ServiceLocatorException {
		Object home = null;
		try {
			if (cache.containsKey(jndiName))
				home = cache.get(jndiName);
			else {
				home = context.lookup(jndiName);
				cache.put(jndiName, home);
			}

			return home;
		} catch (Throwable ex) {
			log.log(Level.SEVERE, ex.getMessage(), ex);
			throw new ServiceLocatorException(ex);
		}
	}
}
