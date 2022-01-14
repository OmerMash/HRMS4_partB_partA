import com.hit.algorithm.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SearchControllerService {

    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private IAlgoSearch algoSearch;
//    public MyDaoFileImpl dao;
    public IDAO dao;
    private List<Person> searchResult;

    //  CONSTRUCTOR
    public SearchControllerService() {
        try {
            dao = new MyDaoFileImpl("person_ser");  //create model object
//            IDAO m = new MyDaoFileImpl("person_ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected Person addPerson(String name, int age, String id, String profession, int years_of_experience) throws IOException {
        Person p = new Person(name, age, id, profession, years_of_experience);
        dao.add(p);
        System.out.println(dao.getList());
        System.out.println("Done adding person");
        return p;
    }

    protected void searchByAge(int min_age, int max_age){
        SearchByAgeImpl search_by_age = new SearchByAgeImpl();
        searchResult = search_by_age.search(dao.getList(), min_age, max_age, "", 0);
        System.out.println("Search by Age results:" + searchResult );
    }

    protected void searchByProfession(String profession){
        SearchByProfessionImpl searchByProfession = new SearchByProfessionImpl();
        searchResult = searchByProfession.search(dao.getList(), 5, 50, profession, 0);
        System.out.println("Search by profession results:" + searchResult );
    }

    protected void searchByMinYearsOfExperience(int min_years_of_experience){
        SearchByMinYOEImpl searchByMinYOE = new SearchByMinYOEImpl();
        searchResult = searchByMinYOE.search(dao.getList(), 5, 50, "DEV", min_years_of_experience);
        System.out.println("Search by profession results:" + searchResult );
    }

    protected boolean save() {
        boolean result = dao.writeListToFile(dao.getList());
        return result;
    }

    protected void removePerson(Person p){
        dao.remove(p);
    }


}