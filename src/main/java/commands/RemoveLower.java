package commands;

import labStuff.LabCollection;
import labStuff.LabWork;
import utilities.LabWorkCreator;

import java.io.Serializable;
import java.util.Vector;

/**
 * Класс команды, удаляющей все элементы меньше указанного
 */

public class RemoveLower implements Commandable, Serializable {
    @Override
    public String execute(Object o) {
        LabWork ref = new LabWorkCreator().create();
        Vector<LabWork> buffer = new Vector<>();
        LabCollection labcollection = new LabCollection();
        labcollection.add(ref);
        for (LabWork elems : LabCollection.collection) {
            if (elems.compareTo(ref)<0) {
                LabCollection.ids.remove(elems.getId());
                buffer.add(elems);
            }
        }
        if (buffer.isEmpty()){
            return "Таких элементов нет";
        } else {
            for (LabWork elems: buffer){
                labcollection.remove(elems);
            }
            return "Элементы меньше данного удалены";
        }
    }

    @Override
    public String getDescription() {
        return ": удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public String getName() {
        return "remove_lower";
    }
}
