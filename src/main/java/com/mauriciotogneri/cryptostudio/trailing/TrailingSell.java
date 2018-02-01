package com.mauriciotogneri.cryptostudio.trailing;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Percentage;

import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.CONTINUE;
import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.ROLLBACK;
import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.SELL;

public class TrailingSell extends Trailing
{
    private final double trailingSell;
    private final double initialPrice;
    private double highestPrice;

    public TrailingSell(double trailingSell, double initialPrice)
    {
        this.trailingSell = trailingSell;
        this.initialPrice = initialPrice;
        this.highestPrice = initialPrice;
    }

    @Override
    public TrailingState update(PriceData priceData)
    {
        double price = priceData.price();

        if (price >= initialPrice)
        {
            if (price > highestPrice)
            {
                highestPrice = price;

                return CONTINUE;
            }
            else if (price < Percentage.decreaseOf(trailingSell, highestPrice))
            {
                return SELL;
            }
            else
            {
                return CONTINUE;
            }
        }
        else
        {
            return ROLLBACK;
        }
    }
}