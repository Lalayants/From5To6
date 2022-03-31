package commands;

import labStuff.LabCollection;
import labStuff.LabWork;
import utilities.LabComparator;

import java.io.Serializable;
import java.util.Vector;

/**
 * Класс команды, выводящий коллекцию в порядке уменьшения
 */

public class PrintDescending implements Serializable,Commandable{
    @Override
    public String execute(Object o) {
        Vector<LabWork> v = LabCollection.getClone();
        v.sort(new LabComparator().reversed());
        return new LabCollection().show_str(v);
    }

    @Override
    public String getDescription() {
        return " : вывести элементы коллекции в порядке убывания";
    }

    @Override
    public String getName() {
        return "print_descending";
    }
}
