package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.analyzer.Session;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

public class BuyingState extends State
{
    private final Session session;
    private final Strategy buyStrategy;

    public BuyingState(Session session)
    {
        this.session = session;
        this.buyStrategy = session.parameters.buyStrategy();
    }

    @Override
    public State update(PriceData priceData)
    {
        buyStrategy.update(priceData);

        if (buyStrategy.isTriggered())
        {
            return new TrailingBuyState(session, priceData.price());
        }
        else
        {
            return this;
        }
    }
}