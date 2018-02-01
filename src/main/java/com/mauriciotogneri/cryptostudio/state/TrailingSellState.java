package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.WatchingBuyEvent;
import com.mauriciotogneri.cryptostudio.model.events.WatchingSellEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.util.Decimal;
import com.mauriciotogneri.cryptostudio.util.Percentage;

public class TrailingSellState extends State
{
    private final Session session;
    private final Operation operation;
    private final double trailingBuy;
    private final double basePrice;
    private double lowestPrice;

    public TrailingSellState(Session session, Operation operation, double basePrice)
    {
        this.session = session;
        this.operation = operation;
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

                WatchingSellEvent watchingSellEvent = new WatchingSellEvent(priceData, boughtPrice, boughtAmount);
                operation.event(watchingSellEvent);

                return new WatchingSellState(session, operation);
            }
            else
            {
                return this;
            }
        }
        else
        {
            operation.event(new WatchingBuyEvent(priceData));

            return new WatchingBuyState(session, operation);
        }
    }
}