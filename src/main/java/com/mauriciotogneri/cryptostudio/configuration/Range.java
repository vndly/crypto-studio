package com.mauriciotogneri.cryptostudio.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Range<T> implements Iterable<T>
{
    private List<T> list = new ArrayList<>();

    protected void add(T element)
    {
        list.add(element);
    }

    @Override
    public Iterator<T> iterator()
    {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> consumer)
    {
        list.forEach(consumer);
    }

    @Override
    public Spliterator<T> spliterator()
    {
        return list.spliterator();
    }
}