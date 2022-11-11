package Polymorphism;

import java.io.BufferedReader;
import java.io.IOException;

public class ID extends Document
{
    public ID (BufferedReader br)
    {
        try
        {
            this.owner=new Person(br);

            System.out.print("id number: ");
            this.number=br.readLine();
        }
        catch(IOException e){throw new RuntimeException("Couldn't create new id");}
    }

    public ID (String name, String secondName, int yearOfBirth)
    {
        this.owner = new Person(name, secondName, yearOfBirth);
        this.number = "";
    }

    public boolean isFitting(String pattern)
    {
        return (this.owner.name.equals(pattern)) || (this.owner.secondName.equals(pattern)) || (this.number.equals(pattern));
    }

    public boolean equals(ID id)
    {
        return (this.toString().equals(id.toString()));
    }

    public String toString()
    {
        return "id number: " + this.number + " owner: " + this.owner.toString();
    }
}
