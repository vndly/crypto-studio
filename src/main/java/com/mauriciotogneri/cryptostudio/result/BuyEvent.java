package com.mauriciotogneri.cryptostudio.result;

public class BuyEvent extends Event
{
    public final double boughtPrice;
    public final double boughtAmount;

    public BuyEvent(long time, double boughtPrice, double boughtAmount)
    {
        super(time);

        this.boughtPrice = boughtPrice;
        this.boughtAmount = boughtAmount;
    }
}