package com.mauriciotogneri.cryptostudio.app;

import com.mauriciotogneri.cryptostudio.api.Klines;
import com.mauriciotogneri.cryptostudio.model.price.CandleStick;
import com.mauriciotogneri.cryptostudio.types.Interval;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestKlines
{
    @Test
    public void testKlines()
    {
        int limit = 10000;

        Klines klines = new Klines("ETHBTC", Interval.ONE_MINUTE, limit);
        List<CandleStick> list = klines.execute();

        Assert.assertTrue(list.size() == limit);
    }
}