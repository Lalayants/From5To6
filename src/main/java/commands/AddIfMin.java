package commands;

import exceptions.IdBusyException;
import labStuff.LabCollection;
import labStuff.LabWork;

import java.io.Serializable;

/**
 * Класс команды добавления элемента, если он будет наименьшним
 */

public class AddIfMin implements Serializable, Commandable {
    /**
     * @param o
     * @return
     */
    @Override
    public String execute(Object o) {
        // LabWork a = new LabWorkCreator().create();
        LabWork a = (LabWork) o;
        if (LabCollection.collection.stream().allMatch(lab -> lab.compareTo(a) > 0)) {
            try {
                a.setId(LabCollection.getFreeId());
            } catch (IdBusyException e) {
                e.printStackTrace();
            }
            a.setCreationDate();
            LabCollection.collection.add(a);
            System.out.println(getName() + " добавил элемент");
            return "Элемент добавлен";
        } else {
            System.out.println(getName() + " не добавил элемент");
            return "Элемент не добавлен";
        }
        //Vector<LabWork> copy= LabCollection.getClone();
        //copy.sort(new LabComparator());
//        try {
//            if (a.compareTo(copy.get(0)) < 0) {
//                LabCollection.collection.add(a);
//                System.out.println("Элемент добавлен");
//            } else {
//                System.out.println("Элемент больше минимального, поэтому не добавлен");
//            }
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("Коллекция пуста, команда не будет выполняться");
//        }

        //return null;
    }

    /**
     * @return
     */
    @Override
    public String getDescription() {
        return ": добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return "add_if_min";
    }
}
