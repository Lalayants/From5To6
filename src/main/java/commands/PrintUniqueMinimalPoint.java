package commands;

import labStuff.LabCollection;
import labStuff.LabWork;

import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Класс команды, выводящий количество уникальных значений полей минимальный балл
 */
public class PrintUniqueMinimalPoint implements Serializable, Commandable {
    @Override
    public String execute(Object o) {
        System.out.println(LabCollection.collection.stream().distinct().collect(
                Collectors.toMap((p) -> p, LabWork::getMinimalPoint)
        ).values().stream().distinct().collect(Collectors.toList()));
//        ArrayList<Double> points = new ArrayList<>();
//        for (LabWork elems : LabCollection.collection) {
//            if (!points.contains(elems.getMinimalPoint()))
//                points.add(elems.getMinimalPoint());
//        }
//        if (!points.isEmpty())
//            System.out.println("Набор уникальных значений минимальных баллов: " + points);
//        else
//            System.out.println("Набор уникальных значений пуст, тк коллекция пуста");
        String s = LabCollection.collection.stream().distinct().collect(
                Collectors.toMap((p) -> p, LabWork::getMinimalPoint)
        ).values().stream().distinct().collect(Collectors.toList()).toString();
        return s;
    }

    @Override
    public String getDescription() {
        return ": вывести уникальные значения поля minimalPoint всех элементов в коллекции";
    }

    @Override
    public String getName() {
        return "print_unique_minimal_point";
    }
}
