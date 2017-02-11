package com.Util;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.jasypt.util.text.BasicTextEncryptor;

/*;

import api.auth.bean.UserBean;
import api.auth.constant.SessionConstants;*/
public final class Util {

    private Util() {

    }

    /*public static String getBrowser(HttpServletRequest request) {
        return new ChannelRecognizer().getBrowser(request
                .getHeader("User-Agent"));
    }*/

    public static String getEncryptedPassword(String password) {
       
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("flipticket");
        String token = textEncryptor.encrypt(password);
        token = token.replaceAll("[^a-zA-Z0-9]+", "");
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

   
    public static int getDayOfWeek(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = 0;

        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
        case Calendar.MONDAY:
            day = Calendar.MONDAY;
            break;
        case Calendar.TUESDAY:
            day = Calendar.TUESDAY;
            break;
        case Calendar.WEDNESDAY:
            day = Calendar.WEDNESDAY;
            break;
        case Calendar.THURSDAY:
            day = Calendar.THURSDAY;
            break;
        case Calendar.FRIDAY:
            day = Calendar.FRIDAY;
            break;
        case Calendar.SATURDAY:
            day = Calendar.SATURDAY;
            break;
        case Calendar.SUNDAY:
            day = Calendar.SUNDAY;
            break;
        default:
            break;
        }
        return day;
    }

   
   /* public static UserBean getCurrentUser(HttpServletRequest request) {
        return (UserBean) request.getSession()
                .getAttribute(SessionConstants.USER_BEAN);
    }
    
    public static BigInteger getUserId(HttpServletRequest request) {
         return ((UserBean) request.getSession()
                .getAttribute(SessionConstants.USER_BEAN)).getId();
    }*/

       

   
}
