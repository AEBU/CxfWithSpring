package uce.edu.bautista.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Set;

/**
 *		Autor Alexis
 */
public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		System.out.println( "Inicializando contexto..." );

//		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext( );
//
//		servletContext.addListener( new ContextLoaderListener(context) );
//		servletContext.addServlet("CXFServlet",CXFNonSpringJaxrsServlet.class);
//		servletContext.setInitParameter("jaxrs.serviceClasses","uce.edu.bautista.services.HolaService");
//		servletContext.setInitParameter("jaxrs.address","/rest");

//2....
//		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext( );
//		context.register(CXFConfig.class);
//		servletContext.addListener( new ContextLoaderListener(context) );
//
//		ServletRegistration.Dynamic registration =
//		servletContext.addServlet("CXFServlet", new CXFServlet());
//		servletContext.setInitParameter("jaxrs.serviceClasses","uce.edu.bautista.services.HolaService");
//		servletContext.setInitParameter("jaxrs.address","/rest");
//		registration.setLoadOnStartup(1);
//		registration.addMapping("/*");

		servletContext.addListener(new ContextLoaderListener(createWebAppContext()));
		addApacheCxfServlet(servletContext);

	}
	private void addApacheCxfServlet(ServletContext servletContext) {
		CXFServlet cxfServlet = new CXFServlet();

		ServletRegistration.Dynamic appServlet = servletContext.addServlet("CXFServlet", cxfServlet);
		appServlet.setLoadOnStartup(1);

		Set<String> mappingConflicts = appServlet.addMapping("/api/*");
	}

	private WebApplicationContext createWebAppContext() {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(CXFConfig.class);
		return appContext;
	}
}
