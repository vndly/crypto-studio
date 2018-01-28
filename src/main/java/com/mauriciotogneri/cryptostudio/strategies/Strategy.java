package com.mauriciotogneri.cryptostudio.strategies;

import com.mauriciotogneri.javautils.Strings;

public abstract class Strategy
{
    public static Strategy fromString(String name)
    {
        if (Strings.equals(name, "GAIN"))
        {
            return new GAIN();
        }
        else if (Strings.equals(name, "LOSS"))
        {
            return new LOSS();
        }
        else if (Strings.equals(name, "HIGHBB"))
        {
            return new HIGHBB();
        }
        else if (Strings.equals(name, "LOWBB"))
        {
            return new LOWBB();
        }
        else if (Strings.equals(name, "SMACROSS"))
        {
            return new SMACROSS();
        }
        else if (Strings.equals(name, "SMAGAIN"))
        {
            return new SMAGAIN();
        }
        else if (Strings.equals(name, "SMASPREAD"))
        {
            return new SMASPREAD();
        }
        else if (Strings.equals(name, "EMACROSS"))
        {
            return new EMACROSS();
        }
        else if (Strings.equals(name, "EMAGAIN"))
        {
            return new EMAGAIN();
        }
        else if (Strings.equals(name, "EMASPREAD"))
        {
            return new EMASPREAD();
        }

        throw new RuntimeException("Invalid strategy: " + name);
    }
}