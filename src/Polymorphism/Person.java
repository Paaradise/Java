package Polymorphism;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

public class Person implements Serializable
{
    String name;
    String secondName;
    int yearOfBirth;

    Person (BufferedReader br)
    {
        try
        {
            System.out.print("name: ");
            this.name=br.readLine();

            System.out.print("second name: ");
            this.secondName=br.readLine();

            System.out.print("year of birth: ");
            this.yearOfBirth=Integer.parseInt(br.readLine());
        }
        catch(IOException e){
            throw new RuntimeException("Couldn't read data.");
        }
    }

    Person(String name, String secondName, int yearOfBirth)
    {
        this.name = name;
        this.secondName = secondName;
        this.yearOfBirth = yearOfBirth;
    }

    public String toString()
    {
        return this.name + " " + this.secondName;
    }
}
