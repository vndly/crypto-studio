package com.mauriciotogneri.cryptostudio.util;

import java.math.BigDecimal;

public class Decimal
{
    public static double roundPrice(double value)
    {
        return round(value, 8);
    }

    public static double roundPercentage(double value)
    {
        return round(value, 2);
    }

    private static double round(double value, int decimals)
    {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(decimals, BigDecimal.ROUND_HALF_UP);

        return bigDecimal.doubleValue();
    }
}