package utilities;

import exceptions.IdBusyException;
import labStuff.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.zone.ZoneRulesException;
import java.util.Vector;

/**
 * Класс для создание объектов коллекции по файл JSON
 */
public class Decode {
    public static void fill(String data) {
        LabCollection LabCollection =new LabCollection();
        Vector<LabWork> collection = new Vector<>();
        Gson gson = new Gson();
        if (data != null) {
            try {
                Vector<LinkedTreeMap> labs = gson.fromJson(data, Vector.class);
                int i = 0;
                for (LinkedTreeMap params : labs) {
                    i++;
                    LabWork lab = new LabWork();
                    if (!params.containsKey("id"))
                        System.out.println("Ошибка в JSON, значение поля ID лабораторной N" + i + " не удалось считать." +
                                "Дальнейшее чтение этого элемента прекращено");
                    else {
                        try {
                            if (((Double) params.get("id")).intValue() < 0)
                                throw new ClassCastException();
                            try {
                                lab.setId(((Double) params.get("id")).intValue());
                            }catch (IdBusyException a){
                                System.out.println("Ошибка, id лабораторной N" + i + " уже занято. Будет задано автоматически сгенерированное значение");
                            }

                            if (!params.containsKey("difficulty")) {
                                System.out.println("Ошибка в JSON, сложность лабораторной N" + i + " не удалось считать. Установлена сложность по умолчанию(HARD)");
                                lab.setDifficulty(Difficulty.HARD);
                            } else
                                lab.setDifficulty(Difficulty.valueOf((String) params.get("difficulty")));

                            if (!params.containsKey("name") || ((String) params.get("name")).equals("")) {
                                System.out.println("Ошибка в JSON, название лабораторной N" + i + " не удалось считать или оно равно \"\". Применится дефолтное имя");
                                lab.setName("noname");
                            } else
                                lab.setName((String) params.get("name"));

                            if (!params.containsKey("minimalPoint") || ((Double) params.get("minimalPoint")).doubleValue() <= 0) {
                                System.out.println("Ошибка в JSON, минимальный балл лабораторной N" + i + " не удалось считать или он <0. Применится дефолтная масса");
                                lab.setMinimalPoint(6.0);
                            } else
                                lab.setMinimalPoint(((Double) params.get("minimalPoint")).doubleValue());

                            if (!params.containsKey("personalQualitiesMinimum") || ((Double) params.get("personalQualitiesMinimum")).doubleValue() <= 0) {
                                System.out.println("Ошибка в JSON, минимальный балл за лк лабораторной N" + i + " не удалось считать или он <0. Применится дефолтная масса");
                                lab.setPersonalQualitiesMinimum(2);
                            } else
                                lab.setPersonalQualitiesMinimum(((Double) params.get("personalQualitiesMinimum")).intValue());



                            Coordinates coordinates = new Coordinates();
                            try {
                                LinkedTreeMap coordParams = (LinkedTreeMap) params.get("coordinates");
                                if (coordParams.containsKey("x"))
                                    coordinates.setX(((Double) coordParams.get("x")).floatValue());
                                else
                                    System.out.println("Ошибка в JSON, значение координаты X лабораторной N" + i + " не удалось считать. Будут установленны дефолтные");
                                if (coordParams.containsKey("y"))
                                    coordinates.setY(((Double) coordParams.get("y")).longValue());
                                else
                                    System.out.println("Ошибка в JSON, значение координаты Y лабораторной N" + i + " не удалось считать. Будут установленны дефолтные");
                            } catch (NullPointerException a) {
                                System.out.println("Ошибка в JSON, значение координат лабораторной N" + i + " не удалось считать. Будут установленны дефолтные");
                            }
                            lab.setCoordinates(coordinates);

                            if (!params.containsKey("creationDate")) {
                                System.out.println("Ошибка в JSON, значение даты создания лабораторной N" + i + " не удалось считать.\n   Не хватает поля creationDate");
                            } else {
                                LinkedTreeMap creationDate = (LinkedTreeMap) params.get("creationDate");
                                if (!creationDate.containsKey("dateTime") || !creationDate.containsKey("zone")) {
                                    System.out.println("Ошибка в JSON, значение даты создания лабораторной N" + i + " не удалось считать.\n   Не хватает поля dateTime или zone");
                                } else {
                                    LinkedTreeMap<String, String> Zone = (LinkedTreeMap) creationDate.get("zone");
                                    LinkedTreeMap dateTime = (LinkedTreeMap) creationDate.get("dateTime");
                                    if (!dateTime.containsKey("date") || !dateTime.containsKey("time") || !Zone.containsKey("id")) {
                                        System.out.println("Ошибка в JSON, значение даты создания лабораторной N" + i + " не удалось считать.\n   Не хватает поля id в zone или date/time в DateTime");
                                    } else {
                                        LinkedTreeMap<String, Double> partsOfDate = (LinkedTreeMap) dateTime.get("date");
                                        LinkedTreeMap<String, Double> partsOfTime = (LinkedTreeMap) dateTime.get("time");
                                        if (!partsOfDate.containsKey("year") || !partsOfDate.containsKey("month") || !partsOfDate.containsKey("day") || !partsOfTime.containsKey("hour") ||
                                                !partsOfTime.containsKey("minute") || !partsOfTime.containsKey("second") || !partsOfTime.containsKey("nano")) {
                                           // System.out.println("Ошибка в JSON, значение даты создания лабораторной N\" + i + \" не удалось считать.\\n   Не хватает поля year/month/day в date или hour/minute/second/nano в time");
                                        } else {
                                            try {
                                                ZoneId zoneId = ZoneId.of(Zone.get("id"));
                                                int year = partsOfDate.get("year").intValue();
                                                int month = partsOfDate.get("month").intValue();
                                                int day = partsOfDate.get("day").intValue();
                                                int hour = partsOfTime.get("hour").intValue();
                                                int minute = partsOfTime.get("minute").intValue();
                                                int second = partsOfTime.get("second").intValue();
                                                int nano = partsOfTime.get("nano").intValue();
                                                lab.setCreationDate(year, month, day, hour, minute, second, nano, zoneId);
                                            } catch (ClassCastException e) {
                                                System.out.println("Ошибка в JSON, значение даты создания лабораторной N" + i + " не удалось считать.\\n   Проверьте, что все поля в dateTime заполнены цифрами, а zone id буквами");
                                            } catch (ZoneRulesException a) {
                                                System.out.println("Ошибка в JSON, значение даты создания лабораторной N" + i + " не удалось считать.\\n    Неизвестное значение zone id");
                                            }
                                        }
                                    }
                                }
                            }
                            if (lab.getCreationDate() == null) {
                                lab.setCreationDate();
                                System.out.println("Значением даты создания лабораторной N" + i + "выставлена дата чтения");
                            }


                            Person p = new Person();
                            if (!params.containsKey("author")) {
                                System.out.println("Ошибка в JSON, значение автора лабораторной N" + i + " не удалось считать.\\n    Будет установлен дефолтный");
                                p.setName("Иван");
                                p.setWeight(74);
                            } else {
                                LinkedTreeMap author = (LinkedTreeMap) params.get("author");

                                if (!author.containsKey("name") || ((String) author.get("name")).equals("")) {
                                    System.out.println("Ошибка в JSON, имя автора лабораторной N" + i + " не удалось считать или оно равно \"\". Применится дефолтное имя");
                                    p.setName("Иван");
                                } else
                                    p.setName((String) author.get("name"));

                                if (!author.containsKey("weight") || ((Double) author.get("weight")).doubleValue() <= 0) {
                                    System.out.println("Ошибка в JSON, массу автора лабораторной N" + i + " не удалось считать или она <0. Применится дефолтная масса");
                                    p.setWeight(75);
                                } else
                                    p.setWeight(((Double) author.get("weight")).floatValue());

                                if (author.containsKey("eyeColor"))
                                    p.setEyeColor(Color.valueOf((String) author.get("eyeColor")));

                                try {
                                    LinkedTreeMap<String, Double> birthday = (LinkedTreeMap) author.get("birthday");
                                    int byear = birthday.get("year").intValue();
                                    int bmonth = birthday.get("month").intValue();
                                    int bday = birthday.get("day").intValue();
                                    p.setBirthday(LocalDate.of(byear, bmonth, bday));
                                } catch (NullPointerException | ClassCastException e) {
                                    System.out.println("В Json нет Др Автора лабораторной работы N "  +i+ " или ошибка в тэге");
                                }
                            }
                            lab.setAuthor(p);
                            collection.add(lab);
                        } catch (ClassCastException e) {
                            System.out.println("id должно быть числом больше 0. Чтение лабораторной N" + i + " остановлено");
                            e.printStackTrace();

                        }
                    }
                }

                LabCollection.setCollection(collection);
                System.out.println("Коллекция успешно заполнена.");
            } catch (JsonSyntaxException e) {
                System.out.println("Ошибка заполнения.Коллекция пустая");
            } catch (NullPointerException e) {
                //e.printStackTrace();
                System.out.println("В файле указаны некорректные данные. Коллекция пустая.");
            }
        }
    }
}

