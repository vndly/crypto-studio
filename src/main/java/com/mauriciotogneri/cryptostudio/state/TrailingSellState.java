package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.PurchaseEvent;
import com.mauriciotogneri.cryptostudio.model.events.SaleEvent;
import com.mauriciotogneri.cryptostudio.model.events.TrailingSellEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.trailing.Trailing;
import com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState;
import com.mauriciotogneri.cryptostudio.trailing.TrailingSell;

import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.ROLLBACK;
import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.SELL;

/**
 * Uses the trailing sell to keep watching the price as long as it
 * continues going up in order to sell at the highest possible price.
 */
public class TrailingSellState extends State
{
    private final Session session;
    private final Operation operation;
    private final Trailing trailingSell;

    public TrailingSellState(Session session, Operation operation, PriceData priceData)
    {
        this.session = session;
        this.trailingSell = new TrailingSell(session.input.trailingProfit, priceData.price());
        this.operation = operation;
        this.operation.event(new TrailingSellEvent(priceData));
    }

    @Override
    public State update(PriceData priceData)
    {
        TrailingState state = trailingSell.update(priceData);

        if (state == SELL)
        {
            PurchaseEvent purchase = operation.purchase();

            operation.event(new SaleEvent(priceData, purchase));
            session.operation(operation);

            return new WatchingBuyState(session, new Operation(), priceData);
        }
        else if (state == ROLLBACK)
        {
            return new WatchingSellState(session, operation, priceData);
        }
        else
        {
            return this;
        }
    }
}