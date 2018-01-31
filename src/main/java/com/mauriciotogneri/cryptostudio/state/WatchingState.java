package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.TrailingBuyEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

/**
 * Uses the buy strategy to determine if it should by the coin.
 */
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
            TrailingBuyEvent trailingBuyEvent = new TrailingBuyEvent(priceData);
            session.event(trailingBuyEvent);

            return new TrailingBuyState(session, priceData.price());
        }
        else
        {
            return this;
        }
    }
}