package com.mauriciotogneri.cryptostudio.source;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.javautils.Strings;

import java.util.List;

public abstract class Source
{
    public abstract List<PriceData> priceData();

    public static Source fromString(String name, String pair, String interval)
    {
        if (Strings.equals(name, "FILE"))
        {
            return new FileSource(pair, interval);
        }

        throw new RuntimeException("Invalid source: " + name);
    }
}