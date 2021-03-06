import java.io.*;

import commands.*;
import labStuff.LabCollection;
import utilities.ConsoleIO;
import utilities.Decode;
import utilities.FileReader;

public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        LabCollection labCollection = new LabCollection();
        Decode.fill(FileReader.readFromFile());
        Invoker inv = new Invoker();
        inv.register(new Add(), new Help(), new Info(), new Show(), new Clear(), new Exit(), new Save(), new UpdateId(),
                new RemoveById(), new RemoveFirst(), new AddIfMin(), new RemoveLower(), new CountLessThanMinimalPoint(),
                new PrintDescending(), new RemoveById(), new PrintUniqueMinimalPoint(), new ExecuteScript());
        //Scanner in = new Scanner(System.in);
//        while (true) {
//            Scanner in = new Scanner(System.in);
//            System.out.print("> ");
//            try {
//                String s = in.nextLine();
//                if (s != "")
//                    inv.execute(s);
//            } catch (NoSuchElementException e) {
//                //System.out.println("Такая комбинация завершает программу");
//                inv.execute("exit");
//
//            }

        String a = "";
        System.out.println("Приложение готово к работе, введите команду, для справки введите help.");
        while (true) {
            System.out.print(">");
            try {
                a = ConsoleIO.ConsoleIn().trim();
                if (!a.equals("null") && !a.equals(""))
                    inv.execute(a);
            }catch (NullPointerException e){

            }






        }


    }
}

