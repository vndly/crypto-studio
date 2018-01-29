package com.mauriciotogneri.cryptostudio.result;

public class Purchase extends Event
{
    public final double boughtPrice;
    public final double boughtAmount;

    public Purchase(long time, double boughtPrice, double boughtAmount)
    {
        super(time);

        this.boughtPrice = boughtPrice;
        this.boughtAmount = boughtAmount;
    }
}