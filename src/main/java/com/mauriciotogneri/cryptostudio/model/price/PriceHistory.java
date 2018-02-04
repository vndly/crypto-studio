package com.mauriciotogneri.cryptostudio.model.price;

public class PriceHistory
{
    private final long time;
    private final double price;

    public PriceHistory(long time, double price)
    {
        this.time = time;
        this.price = price;
    }

    public long time()
    {
        return time;
    }

    public double price()
    {
        return price;
    }
}