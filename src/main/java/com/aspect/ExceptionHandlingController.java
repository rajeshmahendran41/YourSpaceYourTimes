

package com.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.response.CommonResponse;


@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    public ExceptionHandlingController() {
    }

    @ExceptionHandler({ AspectException.class })
    @ResponseBody
    ResponseEntity<?> handleControllerException(AspectException exp ,HttpServletRequest request, Throwable ex) {
    	CommonResponse res =new CommonResponse();
    	res.setMessage(exp.getMessage());
    	res.setStatus(exp.getStatus());
    	res.setError(exp.getError());
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(res,status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
    
    
  /*  public CommonResponse handleException(AspectException pExp,
            HttpServletRequest request, HttpServletResponse response)
            //throws JsonGenerationException, JsonMappingException, IOException 
    {

        CommonResponse primeResponse = new CommonResponse();
        CommonException error = new CommonException();
        StringBuilder message = new StringBuilder("");
        
        if (!Utility.isNull(pExp)) {
            message.append(pExp.getFunctionalMessage());
            error.setFuncMessage("FAILURE");
            error.setTechMessage(pExp.getTechnicalMessage());
            primeResponse.setException(error);
        }
        

        primeResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        primeResponse.setMessage("Some Server Error Occured. We will fix it soon");
        return primeResponse;
    }*/

}