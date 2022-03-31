package labStuff;


import utilities.ConsoleIO;
import utilities.LabComparator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Класс коллекции
 */
public class LabCollection implements Serializable{
    public static Vector<LabWork> collection = new Vector<>();
    public static ArrayList<Integer> ids = new ArrayList<>();
    private static LocalDate creationDate = LocalDate.now();
    public Vector<LabWork> transfer = new Vector<>();





    public LabCollection() {
        ids.add(0);
        creationDate=LocalDate.now();
    }

    public static LocalDate getCreationDate() {
        return creationDate;
    }

    public static Vector<LabWork> getCollection() {
        //Vector<LabWork> a = (Vector<LabWork>) collection.clone();

        return collection;
    }

    public static Vector<LabWork> getClone(){
        Vector<LabWork> aclone = new Vector<>();
        for (LabWork elems: collection){
            LabWork a = new LabWork();
            a.clone(elems);
            aclone.add(a);
        }
        return aclone;
    }

    public static void setCollection(Vector<LabWork> collection) {
        LabCollection.collection = collection;
    }

    public static Boolean checkID(int id) {
        if (!ids.contains(id)) {
            ids.add(id);
            Collections.sort(ids);
            return true;
        } else
            return false;
    }

    public static int getFreeId() {
        if (ids.isEmpty())
            ids.add(0);

        return Collections.max(ids) + 1;
    }

    public void clear() {
        collection.clear();
        ids.clear();
        ids.add(0);
        System.out.println("Коллекция очищена");
    }

    public int getSize() {
        return collection.size();
    }

    public void add(LabWork lab) {
        collection.add(lab);
    }

    public String getInfo() {
        return "Тип коллекции: Vector\nРазмер коллекции: " + this.getSize()+ "\n" +"Дата инициализации: " +  creationDate;
    }

    public static void show(Vector<LabWork> v){
        if (v.size() > 0) {
            System.out.println("__________________________");
            for (LabWork a : v) {
                System.out.println("__________________________");
                System.out.println(a);
            }
            System.out.println("__________________________");
            System.out.println("__________________________");
        } else {
            System.out.println("__________________________");
            ConsoleIO.ConsoleOut("Коллекция пуста\n");
            System.out.println("__________________________");
        }
    }

    public void show() {
        if (getSize() > 0) {
            System.out.println("__________________________");
            for (LabWork a : collection) {
                System.out.println("__________________________");
                System.out.println(a);
            }
            System.out.println("__________________________");
            System.out.println("__________________________");
        } else {
            System.out.println("__________________________");
            ConsoleIO.ConsoleOut("Коллекция пуста\n");
            System.out.println("__________________________");
        }
    }
    public String show_str(){
        Vector<LabWork> v = getClone();
        v.sort(new LabComparator());
        String s = "";
        if (getSize() > 0) {
            s+="__________________________\n";
            for (LabWork a : v) {
                s+="__________________________\n";
                s+=a+"\n";
            }
            s+="__________________________\n";
            s+="__________________________\n";
        } else {
            s+="__________________________\n";
            s+="Коллекция пуста\n";
            s+="__________________________\n";
        }
        return s;
    }
    public String show_str(Vector<LabWork> v){
        String s = "";
        if (getSize() > 0) {
            s+="__________________________\n";
            for (LabWork a : v) {
                s+="__________________________\n";
                s+=a+"\n";
            }
            s+="__________________________\n";
            s+="__________________________\n";
        } else {
            s+="__________________________\n";
            s+="Коллекция пуста\n";
            s+="__________________________\n";
        }
        return s;
    }
    public void synchronize(){
        transfer.addAll(collection);
    }
    public void showt() {
        if (!transfer.isEmpty()) {
            System.out.println("__________________________");
            for (LabWork a : transfer) {
                System.out.println("__________________________");
                System.out.println(a);
            }
            System.out.println("__________________________");
            System.out.println("__________________________");
        } else {
            System.out.println("__________________________");
            ConsoleIO.ConsoleOut("Коллекция пуста\n");
            System.out.println("__________________________");
        }
    }

    public void sort(){
        LabComparator a = new LabComparator();
        collection.sort(a);
    }

    public void remove(LabWork a){
        ids.remove(a.getId());
        collection.remove(a);
    }

}
