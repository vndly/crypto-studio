package com.mauriciotogneri.cryptostudio.app;

import com.mauriciotogneri.cryptostudio.api.Klines;
import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.types.Interval;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestKlines
{
    @Test
    public void testKlines()
    {
        Klines klines = new Klines("ETHBTC", Interval.ONE_MINUTE, 2000);
        List<CandleStick> list = klines.execute();

        Assert.assertTrue(list.size() == 2000);
    }
}