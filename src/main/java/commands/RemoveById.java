package commands;

import labStuff.LabCollection;
import labStuff.LabWork;

import java.io.Serializable;

/**
 * Класс команды, удаляющей элемент по id
 */

public class RemoveById implements Serializable, Commandable{
    @Override
    public String execute(Object o) {
        try {
            LabWork td = null;
            int id = Integer.parseInt((String) o);
            if(LabCollection.collection.stream().anyMatch(lab->lab.getId().equals(id))){
                LabCollection.ids.remove(id);
                LabCollection.collection.remove(LabCollection.collection.stream().filter(lab->lab.getId().equals(id)).findFirst().get());
                return "Удален элемент c id = " + id + "";
            } else
                return "Нет элемента с = " + id;
//            if (LabCollection.ids.contains(id)) {
//                for (LabWork elems : LabCollection.collection) {
//                    if (elems.getId().equals(id)) {
//                        LabCollection.ids.remove(elems.getId());
//                        td = elems;
//                        break;
//                    }
//                }
//                labcollection.remove(td);
//                System.out.println("Элемент удален успешно");
//            } else {
//                System.out.println("Нет элемента с таким ID");
//
        } catch (NumberFormatException e){
            return "Id должен быть целым числом";
        }

    }

    @Override
    public String getDescription() {
        return ": удалить элемент из коллекции по его id";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
}
