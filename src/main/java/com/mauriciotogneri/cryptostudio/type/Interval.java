package com.mauriciotogneri.cryptostudio.type;

import com.mauriciotogneri.javautils.Strings;

public enum Interval
{
    TEN_SECONDS("10s"),
    ONE_MINUTE("1m"),
    THREE_MINUTES("3m"),
    FIVE_MINUTES("5m"),
    FIFTEEN_MINUTES("15m"),
    THIRTY_MINUTES("30m"),
    ONE_HOUR("1h"),
    TWO_HOURS("2h"),
    FOUR_HOURS("4h"),
    SIX_HOURS("6h"),
    EIGHT_HOURS("8h"),
    TWELVE_HOURS("12h"),
    ONE_DAY("1d"),
    THREE_DAYS("3d"),
    ONE_WEEK("1w"),
    ONE_MONTH("1M");

    private final String code;

    Interval(String code)
    {
        this.code = code;
    }

    public static Interval fromCode(String code)
    {
        for (Interval interval : Interval.values())
        {
            if (Strings.equals(interval.code, code))
            {
                return interval;
            }
        }

        throw new RuntimeException("Invalid interval code: " + code);
    }

    public int inSeconds()
    {
        switch (this)
        {
            case TEN_SECONDS:
                return 10;

            case ONE_MINUTE:
                return 60;

            case THREE_MINUTES:
                return 60 * 3;

            case FIVE_MINUTES:
                return 60 * 5;

            case FIFTEEN_MINUTES:
                return 60 * 15;

            case THIRTY_MINUTES:
                return 60 * 30;

            case ONE_HOUR:
                return 60 * 60;

            case TWO_HOURS:
                return 60 * 60 * 2;

            case FOUR_HOURS:
                return 60 * 60 * 5;

            case SIX_HOURS:
                return 60 * 60 * 6;

            case EIGHT_HOURS:
                return 60 * 60 * 8;

            case TWELVE_HOURS:
                return 60 * 60 * 12;

            case ONE_DAY:
                return 60 * 60 * 24;

            case THREE_DAYS:
                return 60 * 60 * 24 * 3;

            case ONE_WEEK:
                return 60 * 60 * 24 * 7;

            case ONE_MONTH:
                return 60 * 60 * 24 * 30;

            default:
                throw new RuntimeException("Invalid interval");
        }
    }

    public int inDays()
    {
        switch (this)
        {
            case TEN_SECONDS:
                return 6 * 60 * 24;

            case ONE_MINUTE:
                return 60 * 24;

            case THREE_MINUTES:
                return 20 * 24;

            case FIVE_MINUTES:
                return 12 * 24;

            case FIFTEEN_MINUTES:
                return 4 * 24;

            case THIRTY_MINUTES:
                return 2 * 24;

            case ONE_HOUR:
                return 24;

            case TWO_HOURS:
                return 12;

            case FOUR_HOURS:
                return 6;

            case SIX_HOURS:
                return 4;

            case EIGHT_HOURS:
                return 3;

            case TWELVE_HOURS:
                return 2;

            case ONE_DAY:
                return 1;

            default:
                throw new RuntimeException("Invalid interval");
        }
    }

    public String code()
    {
        return code;
    }

    @Override
    public String toString()
    {
        return code;
    }
}