package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.StartWatchingEvent;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

public class WatchingState extends State
{
    private final Session session;
    private final Strategy buyStrategy;

    public WatchingState(Session session)
    {
        this.session = session;
        this.buyStrategy = session.input.buyStrategy();
    }

    @Override
    public State update(PriceData priceData)
    {
        buyStrategy.update(priceData);

        if (buyStrategy.isTriggered())
        {
            session.event(new StartWatchingEvent(priceData.time(), priceData.price()));

            return new TrailingBuyState(session, priceData.price());
        }
        else
        {
            return this;
        }
    }
}