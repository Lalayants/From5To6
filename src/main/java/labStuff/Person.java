package labStuff;

import exceptions.ComaInsteadOfDotException;
import utilities.ConsoleIO;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Класс человека
 */

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDate birthday; //Поле может быть null
    private float weight; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле может быть null

    @Override
    public String toString() {
        return
                "\n   Имя: " + name +
                "\n   Дата рождения: " + birthday +
                "\n   Вес: " + weight +
                "\n   Цвет глаз: " + eyeColor;
    }

    public void setBirthday() {
        System.out.print("Введите дату рождения автора цифрами в формате 'дд мм гггг':\n>");
        String data = ConsoleIO.ConsoleIn();
        if (!data.equals("") && !data.equals("null")) {
            String[] date = data.split(" ");
            try {
                int d = Integer.parseInt(date[0]);
                int m = Integer.parseInt(date[1]);
                int y = Integer.parseInt(date[2]);
                if (0 < m && m < 13 && -999999999 < y && y < 999999999) {
                    birthday = LocalDate.of(y, m, d);
                } else {
                    System.out.println("Проверьте, что 0<месяц<13, а год по модулю меньше 999999999");
                    setBirthday();
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e ) {
                System.out.println("Формат даты неверен");
                setBirthday();
            } catch (DateTimeException a) {
                System.out.println("В этом месяце нет такого дня");
                setBirthday();
            }
        }
    }

    public void setEyeColor() {
        Color[] colors = Color.values();
        String s = "";
        for (Color a : colors) {
            s += a.name() + ", ";
        }
        System.out.print("Введите один из перечисленных цветов(" + s + "), который наиболее близок к цвету глаз автора:\n" + ">"); //Так аккуратнее или можно без плюса в одних ""?
        String name = ConsoleIO.ConsoleIn();
        try {
            if (!name.equals("null") && !name.equals(""))
                setEyeColor(Color.valueOf(name.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("Вы ошиблись, такого цвета нет");
            setEyeColor();
        }
    }

    public void setName() {
        System.out.print("Введите имя автора:\n" + ">"); //Так аккуратнее или можно без плюса в одних ""?
        String name = ConsoleIO.ConsoleIn();
        if (name.equals("") || name.equals("null")) {
            System.out.println("Ошибка. Имя не может быть null/пустой строкой");
            setName();
        } else {
            setName(name);
        }
    }

    public void setWeight() {
        System.out.print("Введите вес автора :\n" + ">");
        try {
            String s = ConsoleIO.ConsoleIn();
            if (s.contains(",")) {
                throw new ComaInsteadOfDotException();
            } else if (s.equals("") || s.equals("null")) {
                System.out.println("Не может быть null или \"\" ");
                this.setWeight();
            } else {
                float weight = Float.parseFloat(s);
                if (weight > 0) {
                    setWeight(Float.parseFloat(s));
                } else {
                    System.out.println("Масса должна быть больше 0");
                    setWeight();
                }
            }
        } catch (NumberFormatException a) {
            System.out.println("Нужно ввести массу в формате десятичной дроби разделенной точкой");
            this.setWeight();
        } catch (ComaInsteadOfDotException a) {
            this.setWeight();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }
}
