package com.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
	
	
	@Autowired
	private UserAuthService userAuth;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
    	registry.addInterceptor(userAuth).excludePathPatterns("/api/auth/login","/api/auth/signup");
    
    }

}