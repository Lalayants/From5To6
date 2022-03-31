package commands;

import labStuff.LabCollection;

import java.io.Serializable;

/**
 * Класс команды, удаляющей первый элемент
 */

public class RemoveFirst implements Serializable, Commandable{
    @Override
    public String execute(Object o) {
        LabCollection.collection.remove(0);
        return "Первый элемент удален";
    }

    @Override
    public String getDescription() {
        return ": удалить первый элемент из коллекции";
    }

    @Override
    public String getName() {
        return "remove_first";
    }
}
