import com.hit.algorithm.Person;
import java.util.List;

public interface IDAO {

    List<Person> getList();

    boolean writeListToFile(List<Person> l);

    void add(Person p);

    void remove(Person p);

}
