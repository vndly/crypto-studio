package com.mauriciotogneri.cryptostudio.model.events;

public class Sale extends Event
{
    public final double boughtPrice;
    public final double boughtAmount;

    public final double soldPrice;
    public final double soldAmount;

    public final double profit;

    public Sale(long time, double boughtPrice, double boughtAmount, double soldPrice, double soldAmount)
    {
        super(time);

        this.boughtPrice = boughtPrice;
        this.boughtAmount = boughtAmount;
        this.soldPrice = soldPrice;
        this.soldAmount = soldAmount;

        double boughtTotal = boughtPrice * boughtAmount;
        double soldTotal = soldPrice * soldAmount;
        this.profit = (soldTotal * 100 / boughtTotal) - 100;
    }
}