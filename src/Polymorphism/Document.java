package Polymorphism;

import java.io.Serializable;

public abstract class Document implements Searching, Serializable
{
        Person owner;
        String number;
}
