package com.mauriciotogneri.cryptostudio.app;

import com.mauriciotogneri.cryptostudio.model.price.CandleStick;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.source.FileSource;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.javautils.Resource;

import java.io.File;
import java.util.List;

public class ChartGenerator
{
    public void run(String pair, Interval interval) throws Exception
    {
        String filePath = String.format("chart/%s_%s.csv", pair, interval.code());
        FileSource fileSource = new FileSource(pair, interval.code());
        List<PriceData> priceList = fileSource.priceData();

        StringBuilder csv = new StringBuilder();

        for (PriceData priceData : priceList)
        {
            CandleStick candleStick = (CandleStick) priceData;

            csv.append(candleStick.time());
            csv.append(",");
            csv.append(candleStick.low());
            csv.append(",");
            csv.append(candleStick.open());
            csv.append(",");
            csv.append(candleStick.close());
            csv.append(",");
            csv.append(candleStick.high());
            csv.append("\n");
        }

        Resource.save(new File(filePath), csv.toString());
    }

    public static void main(String[] args) throws Exception
    {
        String pair = "ETHBTC";
        Interval interval = Interval.ONE_MINUTE;

        ChartGenerator chartGenerator = new ChartGenerator();
        chartGenerator.run(pair, interval);
    }
}