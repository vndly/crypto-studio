package com.mauriciotogneri.cryptostudio.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequest
{
    private final OkHttpClient client;

    public HttpRequest()
    {
        this.client = new OkHttpClient();
    }

    public <T> T execute(HttpUrl url, Class<T> clazz) throws Exception
    {
        String body = response(url);

        return new Gson().fromJson(body, clazz);
    }

    public JsonElement execute(HttpUrl url) throws Exception
    {
//        HttpUrl uri = new HttpUrl.Builder()
//                .scheme("http")
//                .host("www.google.com")
//                .addPathSegment("search")
//                .addQueryParameter("q", "polar bears")
//                .build();

        String body = response(url);

        return new JsonParser().parse(body);
    }

    private String response(HttpUrl url) throws Exception
    {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}