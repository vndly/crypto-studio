package com.mauriciotogneri.cryptostudio.app;

import com.mauriciotogneri.cryptostudio.configuration.Range;
import com.mauriciotogneri.cryptostudio.configuration.RangeDouble;
import com.mauriciotogneri.cryptostudio.configuration.RangeString;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRange
{
    @Test
    public void testRangeString()
    {
        List<String> list = new ArrayList<>();
        Range<String> range = new RangeString("a b c");

        for (String element : range)
        {
            list.add(element);
        }

        Assert.assertTrue(list.equals(Arrays.asList("a", "b", "c")));
    }

    @Test
    public void testRangeDouble()
    {
        List<Double> list = new ArrayList<>();
        Range<Double> range = new RangeDouble("1 4 0.5");

        for (Double element : range)
        {
            list.add(element);
        }

        Assert.assertTrue(list.equals(Arrays.asList(1d, 1.5d, 2d, 2.5d, 3d, 3.5d, 4d)));
    }

    @Test
    public void testRangeDoubleSingle()
    {
        List<Double> list = new ArrayList<>();
        Range<Double> range = new RangeDouble("-0.5");

        for (Double element : range)
        {
            list.add(element);
        }

        Assert.assertTrue(list.equals(Arrays.asList(-0.5d)));
    }
}