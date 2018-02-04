package com.mauriciotogneri.cryptostudio.source;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Input;
import com.mauriciotogneri.cryptostudio.type.Interval;

import java.util.List;

public abstract class Source
{
    public enum SourceType
    {
        FILE
    }

    public abstract List<PriceData> priceData(Input input);

    public static Source fromString(SourceType type, String pair, Interval interval)
    {
        switch (type)
        {
            case FILE:
                return new FileSource(pair, interval);

            default:
                throw new RuntimeException("Invalid source: " + type);
        }
    }
}