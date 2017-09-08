package in.ac.amu.zhcet.utils;

import org.apache.commons.text.WordUtils;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Locale;

public class Utils {

    public static String getDefaultSessionCode(){
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();

        String session = "A";
        if (month >= 1 && month < 6)
            session ="W";

        String year = String.valueOf(localDate.getYear() % 100);
        session += year;

        return session;
    }

    public static String getDefaultSessionName() {
        return getSessionName(getDefaultSessionCode());
    }

    public static String getSessionName(String sessionCode) {
        if (sessionCode.charAt(0) == 'A')
            return "Autumn '" + sessionCode.substring(1);
        else
            return "Winter '" + sessionCode.substring(1);
    }

    public static String capitalizeFirst(String string) {
        return WordUtils.capitalizeFully(string.trim());
    }

    public static String capitalizeAll(String string) {
        return string.trim().toUpperCase(Locale.getDefault());
    }

    public static boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
