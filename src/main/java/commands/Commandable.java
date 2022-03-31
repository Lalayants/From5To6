package commands;

import labStuff.LabCollection;


public interface Commandable{
    LabCollection labcollection = new LabCollection();

    String execute(Object o);
    String getDescription();
    String getName();

}
