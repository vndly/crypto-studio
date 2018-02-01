package com.mauriciotogneri.cryptostudio.trailing;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Percentage;

import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.BUY;
import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.CONTINUE;
import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.ROLLBACK;

public class TrailingBuy extends Trailing
{
    private final double trailingBuy;
    private final double initialPrice;
    private double lowestPrice;

    public TrailingBuy(double trailingBuy, double initialPrice)
    {
        this.trailingBuy = trailingBuy;
        this.initialPrice = initialPrice;
        this.lowestPrice = initialPrice;
    }

    @Override
    public TrailingState update(PriceData priceData)
    {
        double price = priceData.price();

        if (price <= initialPrice)
        {
            if (price < lowestPrice)
            {
                lowestPrice = price;

                return CONTINUE;
            }
            else if (price > Percentage.increaseOf(trailingBuy, lowestPrice))
            {
                return BUY;
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