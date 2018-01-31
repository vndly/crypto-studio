package com.mauriciotogneri.cryptostudio.model.events;

public class TrailingBuyEvent extends Event
{
    public final double boughtPrice;
    public final double boughtAmount;

    public TrailingBuyEvent(long time, double boughtPrice, double boughtAmount)
    {
        super("trailing_buy", time);

        this.boughtPrice = boughtPrice;
        this.boughtAmount = boughtAmount;
    }
}