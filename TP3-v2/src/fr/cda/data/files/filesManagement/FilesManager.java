package fr.cda.data.files.filesManagement;

import java.io.*;
import java.util.ArrayList;

public class FilesManager {

    public static void writeDownAll(String className, ArrayList<Object> arrayObj) {

        try {
            // saving path and name according to class name sent
            String pathAndName = "src/fr/cda/data/files/filesStorage/" + className + ".txt";

            // creating instance for file
            FileOutputStream file = new FileOutputStream(pathAndName);
            ObjectOutputStream objectOutput = new ObjectOutputStream(file);

            // loop to write down
            for (Object obj : arrayObj) {
                objectOutput.writeObject(obj);
            }

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static ArrayList<Object> readAll(String className) {
        ArrayList<Object> arrObject = new ArrayList<>();
        boolean finish = false;
        int compt = 0;

        try {
            // saving path and name according to class name sent
            String pathAndName = "src/fr/cda/data/files/filesStorage/" + className + ".txt";

            // researching instance for file
            FileInputStream file = new FileInputStream(pathAndName);
            ObjectInputStream objectInput = new ObjectInputStream(file);


            // loop to read down while there are objects to write
            while (!finish) {
                Object obj = objectInput.readObject();
                arrObject.add(obj);
                compt++;
            }


        } catch (Exception e) {
            finish = true;
//            e.printStackTrace();
        }

        return arrObject;
    }

}
