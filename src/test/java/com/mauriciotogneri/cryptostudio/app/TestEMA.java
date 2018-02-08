package com.mauriciotogneri.cryptostudio.app;

import com.mauriciotogneri.cryptostudio.indicator.EMA;
import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;
import com.mauriciotogneri.cryptostudio.type.Interval;

import org.junit.Assert;
import org.junit.Test;

public class TestEMA
{
    @Test
    public void testEMA()
    {
        double[] data = new double[] {0.101499, 0.101374, 0.101184, 0.101397, 0.101123, 0.101201, 0.101201, 0.101096, 0.101069, 0.100852, 0.100842, 0.100928, 0.100905, 0.100894, 0.100888, 0.100883, 0.100885, 0.100876, 0.100652, 0.10085, 0.100988, 0.101011, 0.100926, 0.101046, 0.100912, 0.10072, 0.100761, 0.100762, 0.100723, 0.10071, 0.100361, 0.10017, 0.10032, 0.0999, 0.100026, 0.100196, 0.100226, 0.099963, 0.100037, 0.099997, 0.099943, 0.099888, 0.100002, 0.099995, 0.099805, 0.099399, 0.099269, 0.099453, 0.099458, 0.099514, 0.099702, 0.09968, 0.099576, 0.0996, 0.099596, 0.099572, 0.099501, 0.099664, 0.099219, 0.099223, 0.099047, 0.099223, 0.099139, 0.099534, 0.09925, 0.099415, 0.09946, 0.099515, 0.099269, 0.099318, 0.09935, 0.099223, 0.099259, 0.099322, 0.099513, 0.099537, 0.099432, 0.099409, 0.09943, 0.099447, 0.099578, 0.099248, 0.099246, 0.099359, 0.099279, 0.099087, 0.098994, 0.099223, 0.099167, 0.09917, 0.099425, 0.099484, 0.099767, 0.099747, 0.100098, 0.100099, 0.100162, 0.099951, 0.1, 0.099694, 0.10008, 0.099993, 0.099928, 0.100126, 0.100284, 0.100158, 0.100256, 0.10001, 0.100034, 0.10026, 0.100272, 0.100318, 0.100298};

        EMA ema = new EMA(Interval.ONE_MINUTE, 60, 24, 12);
        long time = System.currentTimeMillis();
        double lastEMA1 = 0;
        double lastEMA2 = 0;

        for (int i = 0; i < data.length; i++)
        {
            double price = data[i];
            ema.update(new PriceHistory(time + (i * 60000), price));

            lastEMA1 = ema.ema1();
            lastEMA2 = ema.ema2();
        }

        Assert.assertTrue(Double.compare(0.09999459657578587, lastEMA1) == 0);
        Assert.assertTrue(Double.compare(0.10015546097836765, lastEMA2) == 0);
    }
}