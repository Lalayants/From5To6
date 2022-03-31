package labStuff;


import exceptions.*;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Класс координат
 */

public class Coordinates implements Serializable {
    private float x;
    private long y;

    @Override
    public String toString() {
        return "x = " + x +
                " y=" + y;
    }
//    public void Coordinates() {
//        setX();
//    }
//
//
//    public void setX() {
//        System.out.print("Введите координату X :\n" + ">");
//        try {
//            String s = in.nextLine();
//            if (s.contains(",")) {
//                throw new ComaInsteadOfDotException();
//            } else if (s.equals("") || s.equals("null")) {
//                System.out.println("Не может быть null или \"\" ");
//                this.setX();
//            } else {
//                setX(Float.parseFloat(s));
//            }
//        } catch (NumberFormatException a) {
//            System.out.println("Нужно ввести десятичную дробь состоящую из цифр и разделительной точки без использования букв");
//            this.setX();
//        } catch (ComaInsteadOfDotException a) {
//            this.setX();
//        }
//    }
//
//    public void setY() {
//        System.out.print("Введите координату Y :\n" + ">");
//        try {
//            String s = in.nextLine();
//            if (s.equals("") || s.equals("null")) {
//                System.out.println("Не может быть null или \"\" ");
//                this.setY();
//            } else {
//                setY(Long.parseLong(s));
//            }
//        } catch (NumberFormatException a) {
//            System.out.println("Нужно ввести целое число, состоящее только из цифр");
//            this.setY();
//        }
//    }
    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }
}