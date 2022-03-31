package labStuff;

import exceptions.IdBusyException;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Класс лабораторной работы
 */
public class LabWork implements Serializable, Comparable<LabWork>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private transient java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double minimalPoint; //Поле может быть null, Значение поля должно быть больше 0
    private Integer personalQualitiesMinimum; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Person author; //Поле может быть null

    public LabWork(){
        creationDate = ZonedDateTime.now();
        id = LabCollection.getFreeId();
    }
    public void setCreationDate(int y, int m, int d, int h, int minute, int s, int n, ZoneId z){
        creationDate = ZonedDateTime.of(y, m, d, h, minute, s, n, z);
    }

    public void setCreationDate() {
        creationDate = ZonedDateTime.now();
    }
    public void clone(LabWork a){
        id = a.getId();
        name = a.getName();
        coordinates = a.getCoordinates();
        creationDate = a.getCreationDate();
        minimalPoint=a.getMinimalPoint();
        personalQualitiesMinimum=a.getPersonalQualitiesMinimum();
        difficulty=a.getDifficulty();
        author=a.getAuthor();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) throws IdBusyException {
        if(LabCollection.checkID(id)){
            this.id = id;
        } else {
            setId(LabCollection.getFreeId());
            throw new IdBusyException();
        }
    }
    public void setIdBlindly(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Double getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(Double minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public Integer getPersonalQualitiesMinimum() {
        return personalQualitiesMinimum;
    }

    public void setPersonalQualitiesMinimum(Integer personalQualitiesMinimum) {
        this.personalQualitiesMinimum = personalQualitiesMinimum;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public String toString() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
        //String formattedString = creationDate.format(formatter);
        return "Лабораторная работа:" +
                "\n   Название: " + name +
                "\n   id: " + id +
                "\n   Координаты: " + coordinates +
                "\n   Дата создания: " + creationDate +
                "\n   minimalPoint: " + minimalPoint +
                "\n   personalQualitiesMinimum: " + personalQualitiesMinimum +
                "\n   Сложность: " + difficulty +
                "\nАвтор: " + author;
    }

    @Override
    public int compareTo(LabWork o) {
        return name.compareTo(o.getName());
    }
}
