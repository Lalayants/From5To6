package commands;

import labStuff.LabCollection;
import labStuff.LabWork;

import java.io.Serializable;

/**
 * Класс команды, обновляющей элемент по ID
 */

public class UpdateId implements Serializable,Commandable{
    @Override
    public String execute(Object o) {
        try {
            LabWork l = (LabWork) o;
            if (LabCollection.collection.stream().anyMatch(lab->lab.getId().equals(l.getId()))){
                l.setCreationDate();
                LabCollection.collection.remove(LabCollection.collection.stream().filter(lab->lab.getId().equals(l.getId())).findFirst().get());
                LabCollection.collection.add(l);
                return  "Заменен элемент c id = " + l.getId();
            }else {
                return "Нет элемента с таким ID";
            }
//            int id = Integer.parseInt((String) o);
//            if (LabCollection.ids.contains(id)) {
//                for (LabWork elems : LabCollection.collection) {
//                    if (elems.getId().equals(id)) {
//                        LabCollection.ids.remove(elems.getId());
//                        elems.clone(new LabWorkCreator().create(id));
//                    }
//                }
//                System.out.println("Замена прошла успешно");
//            } else {
//                System.out.println("Нет элемента с таким ID");
//            }
        } catch (NumberFormatException e){
            return "Id должен быть целым числом";
        }

    }

    @Override
    public String getDescription() {
        return ": обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getName() {
        return "update";
    }
}
