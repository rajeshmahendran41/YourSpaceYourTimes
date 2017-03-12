package com.Util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import com.aspect.AspectException;
import com.ysyt.bean.Roles;
import com.ysyt.bean.UserBean;
import com.ysyt.constants.SessionConstant;


public final class Util {

    private Util() {

    }


    public static String getEncryptedPassword(String password) {
       
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("usut");
        String token = textEncryptor.encrypt(password);
        return token;
    }
    
    public static String getDecryptedPassword(String password) {
	       
        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword("usut");
        String token = textEncryptor.decrypt(password);
        return token;
        
    }

     public static String trim(String stringValueInp) {
        String stringValue = stringValueInp;
        if (!isNullString(stringValue)) {
            stringValue = stringValue.trim();
        }
        return stringValue;
    }

    public static boolean isNullString(String string) {
        boolean isNullString = true;
        if (string != null && !"".equals(string.trim())) {
            isNullString = false;
        }
        return isNullString;
    }


    public static boolean isNull(Object obj) {
        return null == obj ? true : false;
    }

    @SuppressWarnings("unchecked")
    public static boolean isNullList(Object obj) {
        boolean isNullList = true;
        if (obj != null) {
            if (obj instanceof List) {
                List<Object> list = (List<Object>) obj;
                isNullList = (list.isEmpty() || list.size() < 1);
            }
        }
        return isNullList;
    }

    public static String toUpper(String stringValueInp) {
        String stringValue = stringValueInp;
        if (!isNullString(stringValue)) {
            stringValue = trim(stringValue).toUpperCase();
        }
        return stringValue;
    }

  
    public static Timestamp getCurrentTimeStamp() {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");

        String timeStamp = timeStampFormat.format(Calendar.getInstance()
                .getTime());
        Timestamp ts = Timestamp.valueOf(timeStamp);
        return ts;
    }

      
    public static UserBean getCurrentUser(HttpServletRequest request) {
        return (UserBean) request.getSession()
                .getAttribute(SessionConstant.USER_BEAN);
    }
    
    public static Long getUserId(HttpServletRequest request) {
         return ((UserBean) request.getSession()
                .getAttribute(SessionConstant.USER_BEAN)).getId();
    }
    
    public static Roles getRolesBean(HttpServletRequest request) {
        return ((UserBean) request.getSession()
               .getAttribute(SessionConstant.USER_BEAN)).getRoles();
   }
    
    public static void throwPrimeException(String msg) {
        throw new AspectException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Failure",msg );
    }

    
    public static String[] exculdeApi(){
    	
    	return new String[]{"/api/auth/signup","/api/auth/login","/api/accomodation/locations","/api/accomodation/list","/api/accomodation/filter"};
    	
    }
       

   
}
