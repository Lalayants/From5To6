package commands;

import java.io.Serializable;

/**
 * Класс команды, выводящей справку по коллекции
 */
public class Info implements Serializable,Commandable {

    @Override
    public String execute(Object o) {

        //System.out.println(labcollection.getInfo());
        return labcollection.getInfo();
    }

    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }
}
