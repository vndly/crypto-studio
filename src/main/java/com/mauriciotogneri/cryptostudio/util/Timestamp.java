package com.mauriciotogneri.cryptostudio.util;

import com.mauriciotogneri.javautils.FormattedDateTime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Locale;

public class Timestamp
{
    private static final FormattedDateTime format = new FormattedDateTime("yyyy-MM-dd HH:mm:ss");

    public static String date(long time)
    {
        return format.date(new DateTime(time), DateTimeZone.UTC, Locale.US);
    }
}