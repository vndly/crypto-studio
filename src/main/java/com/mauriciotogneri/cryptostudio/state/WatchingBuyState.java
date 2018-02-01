package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.Event;
import com.mauriciotogneri.cryptostudio.model.events.TrailingBuyEvent;
import com.mauriciotogneri.cryptostudio.model.events.WatchingBuyEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

/**
 * Uses the buy strategy to determine if it should by the coin.
 */
public class WatchingBuyState extends State
{
    private final Session session;
    private final Operation operation;
    private final Strategy buyStrategy;

    public WatchingBuyState(Session session, Operation operation)
    {
        this.session = session;
        this.operation = operation;
        this.buyStrategy = session.input.buyStrategy();
    }

    @Override
    public State update(PriceData priceData)
    {
        if (operation.isEmpty())
        {
            operation.event(new WatchingBuyEvent(priceData));
        }

        buyStrategy.update(priceData);

        if (buyStrategy.isTriggered())
        {
            Event event = new TrailingBuyEvent(priceData);
            operation.event(event);

            return new TrailingBuyState(session, operation, priceData.price());
        }
        else
        {
            return this;
        }
    }
}