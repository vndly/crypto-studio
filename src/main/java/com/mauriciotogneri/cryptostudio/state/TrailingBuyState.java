package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.analyzer.Session;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.events.Purchase;
import com.mauriciotogneri.cryptostudio.util.Decimal;
import com.mauriciotogneri.cryptostudio.util.Percentage;

public class TrailingBuyState extends State
{
    private final Session session;
    private final double trailingBuy;
    private final double basePrice;
    private double lowestPrice;

    public TrailingBuyState(Session session, double basePrice)
    {
        this.session = session;
        this.trailingBuy = session.parameters.trailingBuy;
        this.basePrice = basePrice;
        this.lowestPrice = basePrice;
    }

    @Override
    public State update(PriceData priceData)
    {
        double price = priceData.price();

        if (price <= basePrice)
        {
            if (price < lowestPrice)
            {
                lowestPrice = price;

                return this;
            }
            else if (price > Percentage.increaseOf(trailingBuy, lowestPrice))
            {
                double boughtPrice = Decimal.round(priceData.price());
                double boughtAmount = Decimal.round(session.parameters.maxCost / boughtPrice);
                Purchase purchase = new Purchase(priceData.time(), boughtPrice, boughtAmount);

                return new SellingState(session, purchase);
            }
            else
            {
                return this;
            }
        }
        else
        {
            return new BuyingState(session);
        }
    }
}