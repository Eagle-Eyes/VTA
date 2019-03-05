package ir.vira.travelagency.configuration;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.Servlet;
import java.util.EnumSet;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"ir.vira.travelagency.model"})
public class AppConfiguration implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

    @Autowired
    private AppInterceptor appInterceptor;
    @Autowired
    private Environment env;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Servlet facesServlet() {
        return new FacesServlet();
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(facesServlet(), "*.xhtml");
    }

    @Bean
    public FilterRegistrationBean rewriteFilter() {
        FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
        rwFilter.setDispatcherTypes(
                EnumSet.of(
                        DispatcherType.FORWARD,
                        DispatcherType.REQUEST,
                        DispatcherType.ASYNC,
                        DispatcherType.ERROR)
        );
        rwFilter.addUrlPatterns("*.xhtml");
        return rwFilter;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/naturalPerson/list").setViewName("/jsf/natural_person.xhtml");
        registry.addViewController("/login").setViewName("/jsf/login.xhtml");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterceptor).addPathPatterns("/*").order(0);
    }

    //    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("/resources/").setCachePeriod(3600)
//                .resourceChain(true).addResolver(new PathResourceResolver());
//    }

//    @Bean
//    public ServletContextListener contextLoaderListener() {
//        return new ContextLoaderListener();
//    }
//
//    @Bean
//    public RequestContextListener requestContextListener() {
//        return new RequestContextListener();
//    }
}


