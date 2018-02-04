package com.mauriciotogneri.cryptostudio.app;

import com.mauriciotogneri.cryptostudio.api.Klines;
import com.mauriciotogneri.cryptostudio.model.price.CandleStick;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.type.Pair;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestKlines
{
    @Test
    public void testKlines()
    {
        int limit = 10000;

        Klines klines = new Klines(Pair.ETHBTC, Interval.ONE_MINUTE, limit);
        List<CandleStick> list = klines.execute();

        Assert.assertTrue(list.size() == limit);
    }
}