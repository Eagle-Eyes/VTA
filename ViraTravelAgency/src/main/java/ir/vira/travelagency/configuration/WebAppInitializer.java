//package ir.vira.travelagency.configuration;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.SpringServletContainerInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.request.RequestContextListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//
//import javax.faces.webapp.FacesServlet;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//@Configuration
//public class WebAppInitializer extends SpringServletContainerInitializer {
//
//    private static final Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);
//
//    public void onStartup(ServletContext container) throws ServletException {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(AppConfiguration.class);
//        ctx.setServletContext(container);
//
////        ServletRegistration.Dynamic servlet = container.addServlet( "dispatcherExample", new DispatcherServlet(ctx));
//        ServletRegistration.Dynamic servlet = container.addServlet("Faces Servlet", new FacesServlet());
//        servlet.setLoadOnStartup(1);
//        servlet.addMapping("/faces/*");
//        servlet.addMapping("*.xhtml");
//
//        container.addListener(new ContextLoaderListener());
//        logger.warn("ContextLoaderListener added");
//        container.addListener(new RequestContextListener());
//        logger.warn("RequestContextListener added");
//
//    }
//}