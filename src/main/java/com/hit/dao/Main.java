package com.hit.dao;

import com.hit.algorithm.Person;
import com.hit.controller.SearchControllerService;
import java.io.IOException;

public class Main {

    public static MyDaoFileImpl dao;

    public static void main(String[] args) throws IOException {
        //Example of using IDAO (the Model) threw the controller class with invoking and searching IAlgoSearch (logic jar) from external libraries
        try {
            Person personToDelete;

            SearchControllerService controllerService = new SearchControllerService();

            int listSize = controllerService.getDao().getList().size();
            System.out.println(listSize);

            controllerService.addPerson("omer3", 25, "123445689", "dev", 1);

            personToDelete = controllerService.addPerson("person_to_delete", 25, "123445689", "dev", 1);

            controllerService.removePerson(personToDelete);

            controllerService.save();

            controllerService.searchByAge(20, 45);

            controllerService.searchByProfession("dev");

            controllerService.searchByMinYearsOfExperience(3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
