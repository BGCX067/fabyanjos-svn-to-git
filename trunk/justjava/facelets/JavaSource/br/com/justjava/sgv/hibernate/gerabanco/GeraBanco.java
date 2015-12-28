/**
 * 
 */
package br.com.justjava.sgv.hibernate.gerabanco;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author wav138
 *
 */
public class GeraBanco {
	public static void main(String[] args) {
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure();
		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.create(true, true);
	}

}
