package com.mauriciotogneri.cryptostudio.model.price;

public class PriceData
{
    private final long time;
    private final double price;
    private final double sma1;
    private final double sma2;
    private final double last24H;

    public PriceData(long time, double price, double sma1, double sma2, double last24H)
    {
        this.time = time;
        this.price = price;
        this.sma1 = sma1;
        this.sma2 = sma2;
        this.last24H = last24H;
    }

    public long time()
    {
        return time;
    }

    public double price()
    {
        return price;
    }

    public double sma1()
    {
        return sma1;
    }

    public double sma2()
    {
        return sma2;
    }

    public double last24H()
    {
        return last24H;
    }
}