import com.hit.algorithm.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MyDaoFileImplTest {

    SearchControllerService controllerService = new SearchControllerService();
    Person personToDelete;

    @Test
    void add() {
        try {
        int personListSizeBeforeAddingPersons = controllerService.dao.getList().size();
        int personListSizeAfterAddingPersons;
            controllerService.addPerson("omer3", 25, "123445689", "dev", 1);
            controllerService.addPerson("omer4", 26, "123445679", "dev", 2);
            personListSizeAfterAddingPersons = controllerService.dao.getList().size();
            assertEquals(personListSizeAfterAddingPersons - personListSizeBeforeAddingPersons, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void remove() {
        int personListSizeBeforeAddingPerson = controllerService.dao.getList().size();
        int personListSizeAfterAddingPerson;
        int personListSizeAfterRemovingPerson;
        try {
            personToDelete = controllerService.addPerson("person_to_delete", 25, "123445689", "dev", 1);
            personListSizeAfterAddingPerson = controllerService.dao.getList().size();
            assertEquals(personListSizeBeforeAddingPerson + 1, personListSizeAfterAddingPerson);
            controllerService.removePerson(personToDelete);
            personListSizeAfterRemovingPerson = controllerService.dao.getList().size();
            assertEquals(personListSizeBeforeAddingPerson, personListSizeAfterRemovingPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void writeListToFile() {
        assertTrue(controllerService.save());
    }


}