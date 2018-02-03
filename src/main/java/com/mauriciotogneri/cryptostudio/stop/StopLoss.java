package com.mauriciotogneri.cryptostudio.stop;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Percentage;

public class StopLoss
{
    private final double limitPrice;

    public StopLoss(double initialPrice, double stopLoss)
    {
        this.limitPrice = Percentage.decreaseOf(stopLoss, initialPrice);
    }

    public boolean update(PriceData priceData)
    {
        return (limitPrice != 0) && (priceData.price() < limitPrice);
    }
}