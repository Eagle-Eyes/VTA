package ir.vira.travelagency;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppRunner {
    
    public static void main(String[] args) {
        
        SpringApplication app = new SpringApplication(AppRunner.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setLogStartupInfo(false);
//        app.addListeners(event -> new ContextLoaderListener());
//        app.addListeners(event -> new StartupServletContextListener());
//        app.addListeners(event -> new RequestContextListener());
        ConfigurableApplicationContext applicationContext = app.run(args);
        
        int i = 0;
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(i++ + " ==> " + beanDefinitionName);
        }
    }
}
