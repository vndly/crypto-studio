package com.mauriciotogneri.cryptostudio.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mauriciotogneri.cryptostudio.model.price.CandleStick;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.type.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.HttpUrl;

public class Klines
{
    private final Pair pair;
    private final Interval interval;
    private final Integer limit;

    private static final int MAX_LIMIT = 500;

    public Klines(Pair pair, Interval interval, Integer limit)
    {
        this.pair = pair;
        this.interval = interval;
        this.limit = limit;
    }

    public List<CandleStick> execute()
    {
        HttpRequest httpRequest = new HttpRequest();

        List<CandleStick> list = new ArrayList<>();
        Long endTime = null;

        while (list.size() < limit)
        {
            int size = limit - list.size();
            size = (size > MAX_LIMIT) ? MAX_LIMIT : size;

            List<CandleStick> newElements = execute(httpRequest, endTime, size);
            list.addAll(newElements);

            endTime = newElements.get(newElements.size() - 1).openTime() - 1;

            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        Collections.reverse(list);

        return list;
    }

    private List<CandleStick> execute(HttpRequest httpRequest, Long endTime, int size)
    {
        HttpUrl.Builder url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.binance.com")
                .encodedPath("/api/v1/klines")
                .addQueryParameter("symbol", pair.toString())
                .addQueryParameter("interval", interval.code())
                .addQueryParameter("limit", String.valueOf(size));

        if (endTime != null)
        {
            url.addQueryParameter("endTime", String.valueOf(endTime));
        }

        List<CandleStick> list = new ArrayList<>();

        JsonElement jsonElement = httpRequest.execute(url.build());
        JsonArray array = jsonElement.getAsJsonArray();

        for (int i = 0; i < array.size(); i++)
        {
            JsonArray element = array.get(i).getAsJsonArray();

            list.add(candleStick(element));
        }

        Collections.reverse(list);

        return list;
    }

    private CandleStick candleStick(JsonArray array)
    {
        return new CandleStick(
                array.get(0).getAsLong(),
                array.get(1).getAsDouble(),
                array.get(2).getAsDouble(),
                array.get(3).getAsDouble(),
                array.get(4).getAsDouble(),
                array.get(5).getAsDouble(),
                array.get(6).getAsLong(),
                array.get(7).getAsDouble(),
                array.get(8).getAsInt(),
                array.get(9).getAsDouble(),
                array.get(10).getAsDouble()
        );
    }
}