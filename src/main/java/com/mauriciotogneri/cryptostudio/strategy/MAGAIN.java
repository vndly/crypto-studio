package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Percentage;

public abstract class MAGAIN extends Strategy
{
    private final double buyValue;

    public MAGAIN(double buyValue)
    {
        this.buyValue = buyValue;
    }

    @Override
    public boolean update(PriceData priceData)
    {
        double lowest = lowest(priceData);

        if (lowest != 0)
        {
            if (buyValue < 0)
            {
                return priceData.price() < Percentage.decreaseOf(buyValue, lowest);
            }
            else
            {
                return priceData.price() > Percentage.increaseOf(buyValue, lowest);
            }
        }
        else
        {
            return false;
        }
    }

    protected abstract double lowest(PriceData priceData);
}