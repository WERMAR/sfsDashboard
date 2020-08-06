package com.wpmtec.buildersBoard.util.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO check implementation of date convert cause error in converting date from string to date
public class DateConverter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String convertToString(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static Date convertToDate(String dateString) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
    }
}
