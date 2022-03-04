package Utilities;

import Commands.Exit;
import Commands.Invoker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для чтения и выводы в консоль
 */
public class ConsoleIO {
    private static final Scanner in = new Scanner(System.in);

    public static void ConsoleOut(String s) {
        System.out.print(s);
    }


    //    public static String ConsoleIn(){
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            return reader.readLine();
//        } catch (NoSuchElementException | IOException e) {
//            try {
//                System.in.reset();
//            } catch (IOException ex) {
//                System.out.println("Завершение работы.");
//                return "exit";
//            }
//            return "";
//        }
//    }
    public static String ConsoleIn() {
        System.out.print("> ");
        try {
            String s = in.nextLine();
            return s;
        } catch (NoSuchElementException e) {
            //System.out.println("Такая комбинация завершает программу");
            System.exit(0);
        }
        return "";
    }

    public static String ConsoleIn(Scanner a) {
        System.out.print("> ");
        try {
            String s = a.nextLine();
            return s;
        } catch (NoSuchElementException e) {
            //System.out.println("Такая комбинация завершает программу");
            System.exit(0);
        }
        return "";
    }
}