package com.ckw.common.utils;

import java.security.SecureRandom;
import java.util.Random;

public class VerCodeGenerateUtil {

    private static final String SYMBOLS = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM = new SecureRandom();

    public static String gnerateVercode(){
        char[] numbers = new char[6];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(numbers);
    }


}
