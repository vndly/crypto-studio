package com.mauriciotogneri.cryptostudio.api;

import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.types.Interval;

import java.util.ArrayList;
import java.util.List;

public class Klines
{
    private final String pair;
    private final Interval interval;
    private final Integer limit;

    public Klines(String pair, Interval interval, Integer limit)
    {
        this.pair = pair;
        this.interval = interval;
        this.limit = limit;
    }

    public List<CandleStick> execute()
    {
        return new ArrayList<>(); // TODO
    }
}