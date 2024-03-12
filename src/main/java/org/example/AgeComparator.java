package org.example;

import java.util.Comparator;

import static org.example.Person.getAge;

public class AgeComparator implements Comparator<Person> {


    @Override
    public  int compare(Person o1, Person o2) {
        if(getAge(o1.getBirthDate()) < getAge(o2.getBirthDate())){
            return -1;
        }else if(getAge(o1.getBirthDate()) > getAge(o2.getBirthDate())){
            return 1;
        }else return 0;
    }
}
