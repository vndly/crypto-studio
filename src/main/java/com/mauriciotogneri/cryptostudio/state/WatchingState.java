package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.TrailingBuyEvent;
import com.mauriciotogneri.cryptostudio.model.events.WatchingEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

/**
 * Uses the buy strategy to determine if it should by the coin.
 */
public class WatchingState extends State
{
    private final Session session;
    private final Strategy buyStrategy;
    private final Operation operation;

    public WatchingState(Session session, Operation operation)
    {
        this.session = session;
        this.buyStrategy = session.input.buyStrategy();
        this.operation = operation;
    }

    @Override
    public State update(PriceData priceData)
    {
        if (operation.isEmpty())
        {
            operation.event(new WatchingEvent(priceData));
        }

        buyStrategy.update(priceData);

        if (buyStrategy.isTriggered())
        {
            TrailingBuyEvent trailingBuyEvent = new TrailingBuyEvent(priceData);
            operation.event(trailingBuyEvent);

            return new TrailingBuyState(session, operation, priceData.price());
        }
        else
        {
            return this;
        }
    }
}