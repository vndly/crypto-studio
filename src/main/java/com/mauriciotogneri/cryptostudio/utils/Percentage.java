package com.mauriciotogneri.cryptostudio.utils;

public class Percentage
{
    public static double of(double percentage, double value)
    {
        return value * percentage / 100;
    }

    public static double decreaseOf(double percentage, double value)
    {
        return value - Percentage.of(Math.abs(percentage), value);
    }

    public static double increaseOf(double percentage, double value)
    {
        return value + Percentage.of(Math.abs(percentage), value);
    }
}