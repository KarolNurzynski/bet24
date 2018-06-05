package pl.coderslab.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    // DIRECT ACTIONS: URL->VIEW
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/logout").setViewName("logoutView");
//        registry.addViewController("/login").setViewName("admin/login");
        registry.addViewController("/403").setViewName("403");
    }

}
