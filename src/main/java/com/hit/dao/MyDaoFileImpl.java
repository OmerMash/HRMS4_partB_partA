package com.hit.dao;

import com.hit.algorithm.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyDaoFileImpl implements IDAO {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final List<Person> personList = new ArrayList<Person>();

    //  CONSTRUCTOR
    public MyDaoFileImpl(String fileName) throws IOException {
        try {//read file to input stream and add file content to list
            this.input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
            while(true){
                Person p = (Person) input.readObject();
                personList.add(p);
            }
        } catch (ClassNotFoundException | IOException e) {
        }
    }

    public boolean writeListToFile(List<Person> personsList){
        try {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get("person_ser")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Person p : personsList) {
            try {
                output.writeObject(p);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Person> getList(){ return personList;}

    public void add(Person p){ personList.add(p); }

    public void remove(Person p){ personList.remove(p); }

}