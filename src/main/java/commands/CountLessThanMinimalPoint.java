package commands;

import labStuff.LabCollection;

import java.io.Serializable;

/**
 * Класс команды подсчета элементов, минимальный балл которых меньше заданного
 */
public class CountLessThanMinimalPoint implements Serializable, Commandable{
    @Override
    public String execute(Object o) {
        try {
            int i = 0;
            int ref = Integer.parseInt((String) o);
            return ("Таких " + LabCollection.collection.stream().filter(lab->lab.getMinimalPoint()<ref).count());
//            for (LabWork elems : LabCollection.collection) {
//                if (elems.getMinimalPoint() < ref) {
//                    i++;
//                }
//            }
//            System.out.println("Таких " + i);
        } catch(NumberFormatException e){
            return "MinimalPoint должен быть целым числом";
        }

    }

    @Override
    public String getDescription() {
        return ": вывести количество элементов, значение поля minimalPoint которых меньше заданного";
    }

    @Override
    public String getName() {
        return "count_less_than_minimal_point";
    }
}
