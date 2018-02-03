package com.mauriciotogneri.cryptostudio.source;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

import java.util.List;

public abstract class Source
{
    public enum SourceType
    {
        FILE
    }

    public abstract List<PriceData> priceData();

    public static Source fromString(SourceType type, String pair, String interval)
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