package commands;

import java.io.Serializable;

/**
 * Класс команды очистки коллекции
 */

public class Clear implements Serializable, Commandable{

    @Override
    public String execute(Object o) {
        labcollection.clear();
        return "Коллекция очищена";
    }

    @Override
    public String getDescription() {
        return ": очистить коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
