package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.PurchaseEvent;
import com.mauriciotogneri.cryptostudio.model.events.WatchingEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Session;
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
        this.trailingBuy = session.input.trailingBuy;
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
                double boughtAmount = Decimal.round(session.input.maxCost / boughtPrice);

                PurchaseEvent purchaseEvent = new PurchaseEvent(priceData, boughtPrice, boughtAmount);
                session.event(purchaseEvent);

                return new SellingState(session);
            }
            else
            {
                return this;
            }
        }
        else
        {
            session.event(new WatchingEvent(priceData));

            return new WatchingState(session);
        }
    }
}