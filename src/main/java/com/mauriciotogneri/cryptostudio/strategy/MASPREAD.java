package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

public abstract class MASPREAD extends Strategy
{
    private final double buyValue;

    public MASPREAD(double buyValue)
    {
        this.buyValue = buyValue;
    }

    @Override
    public boolean update(PriceData priceData)
    {
        double spread = spread(priceData);

        if (spread != 0)
        {
            if (buyValue < 0)
            {
                return spread <= buyValue;
            }
            else
            {
                return spread >= buyValue;
            }
        }
        else
        {
            return false;
        }
    }

    protected abstract double spread(PriceData priceData);
}