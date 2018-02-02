package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.TradeBuyEvent;
import com.mauriciotogneri.cryptostudio.model.events.TrailingBuyEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.trailing.Trailing;
import com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState;
import com.mauriciotogneri.cryptostudio.trailing.TrailingBuy;

import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.BUY;
import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.ROLLBACK;

/**
 * Uses the trailing buy to keep watching the price as long as it
 * continues going down in order to buy at the lowest possible price.
 */
public class TrailingBuyState extends State
{
    private final Session session;
    private final Operation operation;
    private final Trailing trailingBuy;

    public TrailingBuyState(Session session, Operation operation, PriceData priceData)
    {
        this.session = session;
        this.trailingBuy = new TrailingBuy(session.input.trailingBuy, priceData.price());
        this.operation = operation;
        this.operation.event(new TrailingBuyEvent(priceData));
    }

    @Override
    public State update(PriceData priceData)
    {
        TrailingState state = trailingBuy.update(priceData);

        if (state == BUY)
        {
            operation.event(new TradeBuyEvent(priceData, session.input.maxCost));

            return new WatchingSellState(session, operation, priceData);
        }
        else if (state == ROLLBACK)
        {
            return new WatchingBuyState(session, operation, priceData);
        }
        else
        {
            return this;
        }
    }
}