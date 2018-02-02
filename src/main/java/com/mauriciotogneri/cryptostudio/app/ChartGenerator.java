package com.mauriciotogneri.cryptostudio.app;

import com.google.gson.Gson;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.source.FileSource;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.util.Decimal;
import com.mauriciotogneri.javautils.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChartGenerator
{
    public void csv(String pair, Interval interval) throws Exception
    {
        String filePath = String.format("chart/%s_%s.csv", pair, interval.code());
        FileSource fileSource = new FileSource(pair, interval.code());
        List<PriceData> priceList = fileSource.priceData();

        StringBuilder csv = new StringBuilder();

        for (PriceData priceData : priceList)
        {
            if (csv.length() != 0)
            {
                csv.append("\n");
            }

            csv.append(priceData.time());
            csv.append(",");
            csv.append(Decimal.roundPrice(priceData.price()));
        }

        Resource.save(new File(filePath), csv.toString());
    }

    public void json(String pair, Interval interval) throws Exception
    {
        String filePath = String.format("chart/%s_%s.json", pair, interval.code());
        FileSource fileSource = new FileSource(pair, interval.code());
        List<PriceData> priceList = fileSource.priceData();

        List<double[]> list = new ArrayList<>();

        for (PriceData priceData : priceList)
        {
            double[] element = new double[2];
            element[0] = priceData.time();
            element[1] = Decimal.roundPrice(priceData.price());

            list.add(element);
        }

        Resource.save(new File(filePath), new Gson().toJson(list));
    }

    public static void main(String[] args) throws Exception
    {
        String pair = "ETHBTC";
        Interval interval = Interval.ONE_MINUTE;

        ChartGenerator chartGenerator = new ChartGenerator();
        chartGenerator.json(pair, interval);
    }
}