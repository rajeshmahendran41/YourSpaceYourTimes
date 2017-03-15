package com.config;

import java.io.IOException;
import java.util.Map;
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
import javax.ws.rs.core.NewCookie;

import org.springframework.stereotype.Component;

import com.Util.Util;

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
	        
	        
	        
	        String token = null;
	        

	        Cookie[] cookies = request.getCookies();
	        
	        for(Cookie cookie : cookies){
	        	
	        	if(cookie.getName().equals("XSRF-TOKEN")){
	        		token = cookie.getValue();
	        	}
	        	
	        }


          if(!isAuthenticating(servletRequest)){
        	  
        	  if (session != null) {
                  String csrfTokenValueInSession = (String) session
                          .getAttribute("X-XSRF-TOKEN");                  
          
			    if (token != null) {
			    	
			    	if(!csrfTokenValueInSession.equals(token)){
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
	                          .getAttribute("X-XSRF-TOKEN");
	        	  }
	                  
	                  if(Util.isNull(csrfTokenValueInSession)){
	        	  
			        	  csrfTokenValueInSession = UUID.randomUUID().toString();
	                  }
	                  
	                  HttpSession httpSession = request.getSession(true);
		        	  httpSession.setAttribute("X-XSRF-TOKEN", csrfTokenValueInSession);
		              response.addHeader("X-XSRF-TOKEN", csrfTokenValueInSession);
				      Cookie cookie = new Cookie("XSRF-TOKEN", csrfTokenValueInSession);
				      cookie.setHttpOnly(false);
				      cookie.setPath("/");
				      response.addCookie(cookie);
          
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
