package org.example;

import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Data
@RequiredArgsConstructor()

public class Person implements Comparable<Person>{
    @NonNull
    private String name;
    @NonNull
    private LocalDate birthDate;
    @NonNull
    private double salary;
    @NonNull
    private Departament departament;

    static Random random = new Random();

    public static List<Person> generatePersons(List<Departament> departaments, int size){
        List<Person> employees = new ArrayList<>();


        for (int i = 0; i < size; i++) {
            employees.add(new Person(Person.getRandomName(),
                    Person.getRandomDate(),
                    Person.getRandomSalary(),
                    departaments.get(random.nextInt(0, departaments.size()))));
        }

        return employees;

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name +
                ", age=" + Person.getAge(birthDate) +
                ", salary=" + String.format("%.02f ", salary) +
                ", " + departament.getName() +
                '}';
    }

    private static LocalDate getRandomDate(){
        int day, month, year;

        day = ThreadLocalRandom.current().nextInt(1, 27);
        month = ThreadLocalRandom.current().nextInt(1, 12);
        year = ThreadLocalRandom.current().nextInt(1967, 2000);
        return LocalDate.of(year, month, day);
    }

    private static String getRandomName(){
        char[] name = new char[15];

        for (int i = 0; i < 15; i++) {
            name[i] = (char)random.nextInt(97, 123);
        }

        return new String(name);
    }
    private static double getRandomSalary(){
        return ThreadLocalRandom.current().nextDouble(20_000, 100_001);
    }

    public static int getAge(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null) {
            return (int)ChronoUnit.YEARS.between(birthDate, currentDate);
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.salary, salary) == 0 && Objects.equals(name, person.name) && Objects.equals(birthDate, person.birthDate) && Objects.equals(departament, person.departament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, salary, departament);
    }




    @Override
    public int compareTo(Person o) {
        if(getAge(this.getBirthDate()) < getAge(o.getBirthDate())){
            return -1;
        }else if(getAge(this.getBirthDate()) > getAge(o.getBirthDate())){
        return 1;
        }else return 0;
    }
}





