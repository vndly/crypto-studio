package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.WatchingBuyEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

/**
 * Uses the buy strategy to determine if it should buy the coin.
 */
public class WatchingBuyState extends State
{
    private final Session session;
    private final Operation operation;
    private final Strategy buyStrategy;

    public WatchingBuyState(Session session, Operation operation, PriceData priceData)
    {
        this.session = session;
        this.buyStrategy = session.input.buyStrategy();
        this.operation = operation;
        this.operation.event(new WatchingBuyEvent(priceData));
    }

    @Override
    public State update(PriceData priceData)
    {
        if (buyStrategy.update(priceData))
        {
            return new TrailingBuyState(session, operation, priceData);
        }
        else
        {
            return this;
        }
    }
}