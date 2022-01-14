import com.hit.algorithm.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyDaoFileImpl implements IDAO{
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private Object Stream;
    private Object FileContent;
    private List<Person> l = new ArrayList<Person>();
    public String fileName = "person_ser";

    //  CONSTRUCTOR
    public MyDaoFileImpl(String fileName) throws IOException {
        try {//read file to input stream and add file content to list
            input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
            while(true){
                Person p = (Person) input.readObject();
                l.add(p);
            }
        } catch (ClassNotFoundException | IOException e) {
        }
    }

    public boolean writeListToFile(List<Person> l){
        try {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get("person_ser")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Person p : l) {
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

    public List<Person> getList(){ return l;}

    public void add(Person p){ l.add(p); }

    public void remove(Person p){ l.remove(p); }

}