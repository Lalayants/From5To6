package commands;

import java.io.Serializable;

/**
 * Класс команды, выводящей справку по всем командам
 */
public class Help implements Commandable, Serializable {
    @Override
    public String execute(Object o) {
        String s = "";
        for (Commandable i: new Invoker().commands.values()){
            s += i.getName() + " " + i.getDescription() + "\n";
        }
        return s;
    }


    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return " : вывести справку по доступным командам";
    }
}
