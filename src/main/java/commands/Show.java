package commands;

import java.io.Serializable;

/**
 * Класс команды, выводящей команду в консоль
 */

public class Show implements Commandable, Serializable {

    @Override
    public String execute(Object o) {
        //labcollection.show();
        return labcollection.show_str();
    }

    @Override
    public String getDescription() {
        return ": показать все элементы коллекции";
    }

    @Override
    public String getName() {
        return "show";
    }
}
