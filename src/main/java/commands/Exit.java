package commands;

import java.io.Serializable;

public class Exit implements Serializable, Commandable{
    @Override
    public String execute(Object o){
//        try {
//            System.in.reset();
//        } catch (IOException e){}
//        Scanner in = new Scanner(System.in);
        System.out.println("Завершение работы...");
//        String anwser = in.nextLine();
//        while (!anwser.toUpperCase().equals("Y") && !anwser.toUpperCase().equals("N")){
//            System.out.println("Введите Y для сохранения перед выходом или N для выхода без сохранения");
//        }
//
//        if (anwser.toUpperCase().equals("Y"))
//            new Save().execute(null);
        System.exit(0);
        return null;
    }

    @Override
    public String getDescription() {
        return ": завершить программу(убедитесь, что данные сохранены заранее)";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
