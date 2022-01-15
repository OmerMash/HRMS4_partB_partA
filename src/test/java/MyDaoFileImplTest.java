import com.hit.algorithm.Person;
import com.hit.controller.SearchControllerService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MyDaoFileImplTest {

    SearchControllerService controllerService = new SearchControllerService();
    Person personToDelete;

    @Test
    void add() throws IOException {
        int personListSizeBeforeAddingPersons = controllerService.getDao().getList().size();
        int personListSizeAfterAddingPersons;
            controllerService.addPerson("omer3", 25, "123445689", "dev", 1);
            controllerService.addPerson("omer4", 26, "123445679", "dev", 2);
            personListSizeAfterAddingPersons = controllerService.getDao().getList().size();
            assertEquals(personListSizeAfterAddingPersons - personListSizeBeforeAddingPersons, 2);
    }

    @Test
    void remove() throws IOException {
        int personListSizeBeforeAddingPerson = controllerService.getDao().getList().size();
        int personListSizeAfterAddingPerson;
        int personListSizeAfterRemovingPerson;
            personToDelete = controllerService.addPerson("person_to_delete", 25, "123445689", "dev", 1);
            personListSizeAfterAddingPerson = controllerService.getDao().getList().size();
            assertEquals(personListSizeBeforeAddingPerson + 1, personListSizeAfterAddingPerson);
            controllerService.removePerson(personToDelete);
            personListSizeAfterRemovingPerson = controllerService.getDao().getList().size();
            assertEquals(personListSizeBeforeAddingPerson, personListSizeAfterRemovingPerson);
    }

    @Test
    void writeListToFile() {
        assertTrue(controllerService.save());
    }


}