package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.Event;
import com.mauriciotogneri.cryptostudio.model.events.WatchingBuyEvent;
import com.mauriciotogneri.cryptostudio.model.events.WatchingSellEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.trailing.Trailing;
import com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState;
import com.mauriciotogneri.cryptostudio.trailing.TrailingBuy;
import com.mauriciotogneri.cryptostudio.util.Decimal;

import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.BUY;
import static com.mauriciotogneri.cryptostudio.trailing.Trailing.TrailingState.ROLLBACK;

public class TrailingBuyState extends State
{
    private final Session session;
    private final Operation operation;
    private final Trailing trailingBuy;

    public TrailingBuyState(Session session, Operation operation, double basePrice)
    {
        this.session = session;
        this.operation = operation;
        this.trailingBuy = new TrailingBuy(session.input.trailingBuy, basePrice);
    }

    @Override
    public State update(PriceData priceData)
    {
        TrailingState state = trailingBuy.update(priceData);

        if (state == BUY)
        {
            double boughtPrice = Decimal.round(priceData.price());
            double boughtAmount = Decimal.round(session.input.maxCost / boughtPrice);

            Event event = new WatchingSellEvent(priceData, boughtPrice, boughtAmount);
            operation.event(event);

            return new WatchingSellState(session, operation);
        }
        else if (state == ROLLBACK)
        {
            operation.event(new WatchingBuyEvent(priceData));

            return new WatchingBuyState(session, operation);
        }
        else
        {
            return this;
        }
    }
}