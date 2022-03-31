package commands;

import exceptions.IdBusyException;
import labStuff.LabCollection;
import labStuff.LabWork;

import java.io.Serializable;

/**
 * Класс команды добавления элемента в коллекцию
 */

public class Add implements Commandable, Serializable {
    /**
     *
     * @param o
     * @return
     */




    @Override
    public String execute(Object o) {
        LabWork a = (LabWork) o;
        try {
            a.setId(LabCollection.getFreeId());
        } catch (IdBusyException e) {
            e.printStackTrace();
        }
        a.setCreationDate();
        labcollection.add(a);
        return "Элемент добавлен";
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return "add";
    }

    /**
     * @return
     */
    public String getDescription(){
        return ": добавить новый элемент в коллекцию";
    }
}
