package com.hit.controller;

import com.hit.dao.*;
import com.hit.algorithm.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SearchControllerService {

    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private IAlgoSearch algoSearch;

    public IDAO getDao() {
        return dao;
    }

    private IDAO dao;
    private List<Person> searchResult;

    //  CONSTRUCTOR
    public SearchControllerService() {
        try {
            this.dao = new MyDaoFileImpl("person_ser");  //create model object
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person addPerson(String name, int age, String id, String profession, int years_of_experience) throws IOException {
        Person p = new Person(name, age, id, profession, years_of_experience);
        dao.add(p);
        System.out.println(dao.getList());
        System.out.println("Done adding person");
        return p;
    }

    public void searchByAge(int min_age, int max_age){
        SearchByAgeImpl search_by_age = new SearchByAgeImpl();
        searchResult = search_by_age.search(dao.getList(), min_age, max_age, "", 0);
        System.out.println("Search by Age results:" + searchResult );
    }

    public void searchByProfession(String profession){
        SearchByProfessionImpl searchByProfession = new SearchByProfessionImpl();
        searchResult = searchByProfession.search(dao.getList(), 5, 50, profession, 0);
        System.out.println("Search by profession results:" + searchResult );
    }

    public void searchByMinYearsOfExperience(int min_years_of_experience){
        SearchByMinYOEImpl searchByMinYOE = new SearchByMinYOEImpl();
        searchResult = searchByMinYOE.search(dao.getList(), 5, 50, "DEV", min_years_of_experience);
        System.out.println("Search by profession results:" + searchResult );
    }

    public boolean save() {
        return dao.writeListToFile(dao.getList());
    }

    public void removePerson(Person p){
        dao.remove(p);
    }


}