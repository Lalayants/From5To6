package commands;

import exceptions.IdBusyException;
import labStuff.*;
import utilities.LabComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 *Класс команды по запуску скрипта из указанного файла
 */

public class ExecuteScript implements Serializable, Commandable {
    @Override
    public String execute(Object o) {
        String START = (String) o;
        Invoker inv = new Invoker();
        inv.register(new Add(), new Help(), new Info(), new Show(), new Clear(), new Exit(), new Save(), new UpdateId(),
                new RemoveById(), new RemoveFirst(), new AddIfMin(), new RemoveLower(), new CountLessThanMinimalPoint(),
                new PrintDescending(), new RemoveById(), new PrintUniqueMinimalPoint());

        File file = new File((String) o);
        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String str = in.nextLine();
                String[] ComAndArg = str.trim().split(" ");
                if (str.trim().isEmpty())
                    continue;

                switch (ComAndArg[0]) {
                    //case "help": inv.execute("help");
                    //case "info":
                    case "add":
                        LabWork lab = new LabWork();
                        Boolean reading = true;
                        String buffer = "";
                        int counter = 0;
                        while (reading) {
                            String arg = in.nextLine().trim();
                            counter++;
                            if (!inv.check(arg) && counter < 10) {
                                buffer += " " + arg.trim();
                            } else {
                                buffer += " " + arg.trim();
                                reading = false;
                            }

                        }
                        String[] data = buffer.trim().split(" ");
                        if (data.length == 12)
                            try {
                                lab.setCreationDate();
                                lab.setId(LabCollection.getFreeId());
                                lab.setName(data[0]);
                                lab.setMinimalPoint(Double.parseDouble(data[1]));
                                lab.setPersonalQualitiesMinimum(Integer.parseInt(data[2]));
                                Coordinates c = new Coordinates();
                                c.setX(Float.parseFloat(data[3]));
                                c.setY(Long.parseLong(data[4]));
                                lab.setCoordinates(c);
                                Person auth = new Person();
                                auth.setName(data[5]);
                                auth.setWeight(Float.parseFloat(data[6]));
                                auth.setEyeColor(Color.valueOf(data[7].toUpperCase()));
                                auth.setBirthday(LocalDate.of(Integer.parseInt(data[10]), Integer.parseInt(data[9]), Integer.parseInt(data[8])));
                                lab.setDifficulty(Difficulty.valueOf(data[11].toUpperCase()));
                                lab.setAuthor(auth);
                                LabCollection.collection.add(lab);
                                break;
                            } catch (Exception e) {
                                System.out.println("В скрипте ошибка, команда add не выполнена");
                                break;
                            }
                        else {
                            System.out.println("В скрипте команде add не хватает аргументов");
                            break;
                        }
                    case"execute_script":
                        try {
                            Object argument = ComAndArg[1];
                            Commandable command = new Invoker().commands.get(ComAndArg[0]);

                                if(!Objects.equals(argument, START)) {
                                    command.execute(argument);
                                    break;
                                }else{
                                    System.out.println("В скрипте рекурсия, команда пропущена");
                                    break;
                                }

                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Ошибка, должно быть имя файла");
                            break;
                        }
                    case "add_if_min":
                        lab = new LabWork();
                        reading = true;
                        buffer = "";
                        counter = 0;
                        while (reading) {
                            String arg = in.nextLine().trim();
                            counter++;
                            if (!inv.check(arg) && counter < 10) {
                                buffer += " " + arg.trim();
                            } else {
                                buffer += " " + arg.trim();
                                reading = false;
                            }

                        }
                        data = buffer.trim().split(" ");
                        if (data.length == 12)
                            try {
                                lab.setCreationDate();
                                lab.setId(LabCollection.getFreeId());
                                lab.setName(data[0]);
                                lab.setMinimalPoint(Double.parseDouble(data[1]));
                                lab.setPersonalQualitiesMinimum(Integer.parseInt(data[2]));
                                Coordinates c = new Coordinates();
                                c.setX(Float.parseFloat(data[3]));
                                c.setY(Long.parseLong(data[4]));
                                lab.setCoordinates(c);
                                Person auth = new Person();
                                auth.setName(data[5]);
                                auth.setWeight(Float.parseFloat(data[6]));
                                auth.setEyeColor(Color.valueOf(data[7].toUpperCase()));
                                auth.setBirthday(LocalDate.of(Integer.parseInt(data[10]), Integer.parseInt(data[9]), Integer.parseInt(data[8])));
                                lab.setDifficulty(Difficulty.valueOf(data[11].toUpperCase()));
                                lab.setAuthor(auth);
                                Vector<LabWork> copy= LabCollection.getClone();
                                copy.sort(new LabComparator());
                                try {
                                    if (lab.compareTo(copy.get(0)) < 0) {
                                        LabCollection.collection.add(lab);
                                        System.out.println("Элемент из скрипта добавлен");
                                    } else {
                                        System.out.println("Элемент из скрипта больше минимального, поэтому не добавлен");
                                    }
                                }catch (IndexOutOfBoundsException e){
                                    System.out.println("Коллекция пуста, команда не будет выполняться");
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("В скрипте ошибка, команда add не выполнена");
                                break;
                            }
                        else {
                            System.out.println("В скрипте команде add_if_min не хватает аргументов");
                            break;
                        }
                    case"update_id":
                         lab = new LabWork();
                        reading = true;
                         buffer = "";
                         counter = 0;
                        while (reading) {
                            String arg = in.nextLine().trim();
                            counter++;
                            if (!inv.check(arg) && counter < 10) {
                                buffer += " " + arg.trim();
                            } else {
                                buffer += " " + arg.trim();
                                reading = false;
                            }

                        }
                         data = buffer.trim().split(" ");
                        if (data.length == 12)
                            try {
                                lab.setCreationDate();
                                lab.setId(LabCollection.getFreeId());
                                lab.setName(data[0]);
                                lab.setMinimalPoint(Double.parseDouble(data[1]));
                                lab.setPersonalQualitiesMinimum(Integer.parseInt(data[2]));
                                Coordinates c = new Coordinates();
                                c.setX(Float.parseFloat(data[3]));
                                c.setY(Long.parseLong(data[4]));
                                lab.setCoordinates(c);
                                Person auth = new Person();
                                auth.setName(data[5]);
                                auth.setWeight(Float.parseFloat(data[6]));
                                auth.setEyeColor(Color.valueOf(data[7].toUpperCase()));
                                auth.setBirthday(LocalDate.of(Integer.parseInt(data[10]), Integer.parseInt(data[9]), Integer.parseInt(data[8])));
                                lab.setDifficulty(Difficulty.valueOf(data[11].toUpperCase()));
                                lab.setAuthor(auth);
                            } catch (Exception e) {
                                System.out.println("В скрипте ошибка, команда add не выполнена");
                                break;
                            }
                        else {
                            System.out.println("В скрипте команде add не хватает аргументов");
                            break;
                        }
                        try {
                            int id = Integer.parseInt((String) o);
                            if (LabCollection.ids.contains(id)) {
                                for (LabWork elems : LabCollection.collection) {
                                    if (elems.getId().equals(id)) {
                                        LabCollection.ids.remove(elems.getId());
                                        elems.clone(lab);

                                    }
                                }
                                System.out.println("Замена прошла успешно");
                                break;
                            } else {
                                System.out.println("Нет элемента с таким ID");
                                break;
                            }
                        } catch (NumberFormatException e){
                            System.out.println("Id должен быть целым числом в команде update id скрипта");
                            break;
                        }

                    case "remove_lower":
                        lab = new LabWork();
                        reading = true;
                        buffer = "";
                        counter = 0;
                        while (reading) {
                            String arg = in.nextLine().trim();
                            counter++;
                            if (!inv.check(arg) && counter < 10) {
                                buffer += " " + arg.trim();
                            } else {
                                buffer += " " + arg.trim();
                                reading = false;
                            }

                        }
                        data = buffer.trim().split(" ");
                        if (data.length == 12)
                            try {
                                lab.setCreationDate();
                                lab.setId(LabCollection.getFreeId());
                                lab.setName(data[0]);
                                lab.setMinimalPoint(Double.parseDouble(data[1]));
                                lab.setPersonalQualitiesMinimum(Integer.parseInt(data[2]));
                                Coordinates c = new Coordinates();
                                c.setX(Float.parseFloat(data[3]));
                                c.setY(Long.parseLong(data[4]));
                                lab.setCoordinates(c);
                                Person auth = new Person();
                                auth.setName(data[5]);
                                auth.setWeight(Float.parseFloat(data[6]));
                                auth.setEyeColor(Color.valueOf(data[7].toUpperCase()));
                                auth.setBirthday(LocalDate.of(Integer.parseInt(data[10]), Integer.parseInt(data[9]), Integer.parseInt(data[8])));
                                lab.setDifficulty(Difficulty.valueOf(data[11].toUpperCase()));
                                lab.setAuthor(auth);
                                LabCollection.collection.add(lab);

                                Vector<LabWork> buff = new Vector<>();


                                for (LabWork elems : LabCollection.collection) {
                                    if (elems.compareTo(lab)<0) {
                                        LabCollection.ids.remove(elems.getId());
                                        buff.add(elems);
                                    }
                                }
                                if (buff.isEmpty()){
                                    System.out.println("Таких элементов нет");
                                    break;
                                } else {
                                    for (LabWork elems: buff){
                                        labcollection.remove(elems);
                                    }
                                    System.out.println("Элементы меньше данного удалены");
                                    break;
                                }
                            }catch (Exception e) {
                        System.out.println("В скрипте ошибка, команда add не выполнена");
                        break;
                    }



                    default:
                        Object argument = null;
                        Commandable command = new Invoker().commands.get(ComAndArg[0]);
                        try {
                            if (command == null)
                                throw new IllegalStateException();

                            if (ComAndArg.length == 2)
                                argument = ComAndArg[1];

                            command.execute(argument);
                        } catch (IllegalStateException e) {
                            System.out.println("Команды " +ComAndArg[0]+ " из скрипта не существует, введите \"help\", чтобы ознакомиться со всем перечнем команд.");
                        }

                }

            }
        } catch (FileNotFoundException | IdBusyException e) {
            System.out.println("Файл не существует или закрыт для чтения");
        }
        return "done";
    }

//    class LabWorkCreate {
//        private Scanner in;
//
//        LabWorkCreate(Scanner in) {
//            LabWork a = new LabWork();
//        }
//
//        public LabWork create() {
//            LabWork lab = new LabWork();
//            lab.setCreationDate();
//            setId(lab);
//
//            return lab;
//        }
//
//        private void setId(LabWork lab) {
//            try {
//                lab.setId(LabCollection.getFreeId());
//            } catch (IdBusyException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private static void setPersonalQualitiesMinimum(LabWork l) {
//            System.out.print("Введите минимальный балл за личные качества:\n" + ">");
//            try {
//                String s = ConsoleIO.ConsoleIn();
//                if (s.equals("") || s.equals("null")) {
//                    System.out.println("Не может быть null или \"\" ");
//                    setPersonalQualitiesMinimum(l);
//                } else {
//                    int point = Integer.parseInt(s);
//                    if (point > 0) {
//                        l.setPersonalQualitiesMinimum(point);
//                    } else {
//                        System.out.println("Балл должен быть больше 0");
//                        setPersonalQualitiesMinimum(l);
//                    }
//                }
//            } catch (NumberFormatException a) {
//                System.out.println("Нужно ввести балл в формате целого числа");
//                setPersonalQualitiesMinimum(l);
//            }
//        }
//
//
//    }

    @Override
    public String getDescription() {
        return ": выполняет набор команд из файла";
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}
