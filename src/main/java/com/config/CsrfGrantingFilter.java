package com.config;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.HTTPUtilities;
import org.owasp.esapi.reference.DefaultHTTPUtilities;
import org.springframework.stereotype.Component;

import com.Util.Util;
import com.ysyt.constants.SessionConstant;

@Component
public class CsrfGrantingFilter implements Filter {
	  @Override
	  public void init(FilterConfig filterConfig) throws ServletException {}

	  @Override
	  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	      throws IOException, ServletException {
		  
		    HttpServletRequest request = (HttpServletRequest) servletRequest;
		    HttpServletResponse response = (HttpServletResponse) servletResponse;
	        HttpSession session = request.getSession(false);

		  
          final String token = request.getHeader("X-Csrf-Token");

          if(!isAuthenticating(servletRequest)){
        	  
        	  if (session != null) {
                  String csrfTokenValueInSession = (String) session
                          .getAttribute("X-Csrf-Token");                  
          
			    if (token != null) {
			    	
			    	if(csrfTokenValueInSession.equals(token)){
				      response = (HttpServletResponse) servletResponse;
				      Cookie cookie = new Cookie("CSRF-TOKEN", token);
				      cookie.setPath("/");
				      response.addCookie(cookie);
			    	}else{
			    		handleInvalidRequest(response);
				    	return;
			    	}
			    }else{
			    	handleInvalidRequest(response);
			    	return;
			    }
        	  }else{
        		  handleInvalidRequest(response);
        		  return;
        	  }
          }else{  
        	  
        	  if(loginUrl(servletRequest)){
        	  
	        	  String csrfTokenValueInSession = null ;
	        	  if (session != null) {
	                   csrfTokenValueInSession = (String) session
	                          .getAttribute("X-Csrf-Token");
	        	  }
	                  
	                  if(Util.isNull(csrfTokenValueInSession)){
	        	  
			        	  String randomValue = UUID.randomUUID().toString();
			        	  HttpSession httpSession = request.getSession(true);
			        	  httpSession.setAttribute("X-Csrf-Token", randomValue);
			              response.addHeader("X-Csrf-Token", randomValue);
	                  }
          
        	  }
        	  
          }
	    filterChain.doFilter(servletRequest, servletResponse);
	  }
	  
	    private void handleInvalidRequest(HttpServletResponse response)
	            throws IOException {
	        response.getOutputStream().print("Missing or non-matching CSRF-token");
	       // HTTPUtilities util = new DefaultHTTPUtilities();
	        //util.setContentType(response);
	        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	        response.resetBuffer();
	    }

	  private boolean isAuthenticating(ServletRequest servletRequest) {
	    HttpServletRequest request = (HttpServletRequest) servletRequest;
	    
	    for(String excludeApi : Util.exculdeApi()){
	    
	    	if( request.getRequestURI().equals(excludeApi)){
	    		return true ;
	    	}
	    
	    }
	    return false;
	    
	    
	  }
	  
	  private boolean loginUrl(ServletRequest servletRequest) {
		    HttpServletRequest request = (HttpServletRequest) servletRequest;
		    
		    	if( request.getRequestURI().equals("/api/auth/login")){
		    		return true ;
		    	}
		    
		    return false;
		    
		    
		  }

	  @Override
	  public void destroy() {}
	}
