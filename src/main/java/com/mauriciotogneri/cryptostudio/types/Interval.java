package com.mauriciotogneri.cryptostudio.types;

public enum Interval
{
    ONE_MINUTE("1m");

    private final String code;

    Interval(String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return code;
    }
}