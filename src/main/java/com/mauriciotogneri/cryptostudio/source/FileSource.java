package com.mauriciotogneri.cryptostudio.source;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.model.PriceData;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class FileSource extends Source
{
    private final String pair;
    private final String interval;

    public FileSource(String pair, String interval)
    {
        this.pair = pair;
        this.interval = interval;
    }

    @Override
    public List<PriceData> priceData()
    {
        try
        {
            InputStream inputStream = new FileInputStream(String.format("data/%s_%s.json", pair, interval));
            String json = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();

            return new Gson().fromJson(json, new TypeToken<List<CandleStick>>()
            {
            }.getType());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}