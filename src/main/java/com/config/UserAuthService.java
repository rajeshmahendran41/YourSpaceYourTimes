package com.config;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.Util.Util;

@Component
public class UserAuthService implements HandlerInterceptor {  

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) {
        Boolean preHandle = false;
        
        if(!request.getMethod().equals("OPTIONS")){

	        HttpSession session = request.getSession(false);
	        if (request.isRequestedSessionIdValid() && session != null) {
	        	
	        	if(!Util.isNull(Util.getCurrentUser(request))){
	        		preHandle = true;
	        	}
	        	else {
	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            }
	        } else {
	            // set the response to access forbidden
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	
	        }
        }
        else{
    		preHandle = true;

        }
        return preHandle;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        Logger.getGlobal();
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        Logger.getGlobal();
    }
}