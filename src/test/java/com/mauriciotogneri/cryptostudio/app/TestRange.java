package com.mauriciotogneri.cryptostudio.app;

import com.mauriciotogneri.cryptostudio.parameters.Range;
import com.mauriciotogneri.cryptostudio.parameters.RangeFloat;
import com.mauriciotogneri.cryptostudio.parameters.RangeString;

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
    public void testRangeFloat()
    {
        List<Float> list = new ArrayList<>();
        Range<Float> range = new RangeFloat("1 4 0.5");

        for (Float element : range)
        {
            list.add(element);
        }

        Assert.assertTrue(list.equals(Arrays.asList(1f, 1.5f, 2f, 2.5f, 3f, 3.5f, 4f)));
    }
}