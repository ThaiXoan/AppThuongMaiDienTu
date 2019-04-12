package it.dut.thaixoan.applazada.until;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validates {

    public static final String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";

    public boolean isEmpty(String chuoiKiemTra) {
        return !"".equals(chuoiKiemTra) && chuoiKiemTra != null;
    }

    public boolean isEmailMatcher(String chuoiKiemTra) {
        return Patterns.EMAIL_ADDRESS.matcher(chuoiKiemTra).matches();
    }

    public boolean isEqualsPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public boolean isPasswordMathcher(String chuoiKiemTra){
        Pattern pattern = Pattern.compile(MATCHER_PATTERN);
        Matcher matcher = pattern.matcher(chuoiKiemTra);
        return matcher.matches();
    }
}
