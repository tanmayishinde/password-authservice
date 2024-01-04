package com.microservice.passwordmanagerservice.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationUtil {

    public static boolean inValidEmailCheck(String email) {
        if (email.isEmpty()){
            return true;
        }
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean inValidatePhoneNumberCheck(String phoneNumber) {
        Pattern ptrn = Pattern.compile("[789]{1}[0-9]{9}");
        //the matcher() method creates a matcher that will match the given input against this pattern
        Matcher match = ptrn.matcher(phoneNumber);
        //returns a boolean value
        if (!match.matches()) {
            return true;
        }
        return false;
    }

    public static boolean inValidatePassword(String password) {
//        ^ represents starting character of the string.
//        (?=.*[0-9]) represents a digit must occur at least once.
//        (?=.*[a-z]) represents a lower case alphabet must occur at least once.
//        (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
//        (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
//        (?=\\S+$) white spaces don’t allowed in the entire string.
//.{8, 20} represents at least 8 characters and at most 20 characters.
//                $ represents the end of the string.

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Pattern ptrn = Pattern.compile(regex);
        //the matcher() method creates a matcher that will match the given input against this pattern
        Matcher match = ptrn.matcher(password);
        //returns a boolean value
        if (!match.matches()) {
            return true;
        }
        return false;
    }


}
