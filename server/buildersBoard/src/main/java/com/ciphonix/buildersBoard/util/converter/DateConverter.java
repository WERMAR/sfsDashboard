package com.ciphonix.buildersBoard.util.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String convertToString(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static Date convertToDate(String dateString) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
    }
}
