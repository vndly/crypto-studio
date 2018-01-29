package com.mauriciotogneri.cryptostudio.utils;

import java.math.BigDecimal;

public class Decimal
{
    public static double round(double value)
    {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(8, BigDecimal.ROUND_HALF_UP);

        return bigDecimal.doubleValue();
    }
}