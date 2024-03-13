package org.example;


import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;


public class App {
    public static void main(String[] args) {
        List<Departament> departaments = Departament.generateDepartaments(5);
        List<Person> employees = Person.generatePersons(departaments, 30);

        employees.forEach(System.out::println);
        /**
         * Вывести на консоль отсортированные (по алфавиту) имена персонов
         */

        System.out.println("-------1 TASK--------------");

        printNamesOrdered(employees);
        System.out.println("-------1 TASK--------------"+"\n");

    /**
     * В каждом департаменте найти самого взрослого сотрудника.
     *    * Вывести на консоль мапипнг department -> personName
     *    * Map<Department, Person>
     */

        System.out.println("-------2 TASK--------------");
        Map<Departament, Person> oldestPersons = printDepartmentOldestPerson(employees);
        oldestPersons.forEach((key, value) -> System.out.printf("%s %s\n", key.getName(),value.toString()));

        System.out.println("-------2 TASK--------------"+"\n");

        /**
         *    * Найти 10 первых сотрудников, младше 30 лет, у которых зарплата выше 50_000
         *
         */

        System.out.println("-------3 TASK--------------");
        List<Person> first10youngEmp = findFirstPersons(employees);
        first10youngEmp.forEach(System.out::println);

        System.out.println("-------3 TASK--------------"+"\n");


        /**
         * Найти депаратмент, чья суммарная зарплата всех сотрудников максимальна
         */
        System.out.println("-------4 TASK--------------");

        Map<Departament, Double> sumSalaryInDept = findTopDepartment(employees);

        sumSalaryInDept.forEach((key, value) -> System.out.println(key + "------>" + value));

        Departament dept = topDepartment(sumSalaryInDept);
        System.out.println("The highest summary of salaries in "+ dept.getName());

        System.out.println("-------4 TASK--------------"+"\n");
    }
    public static void printNamesOrdered(List<Person> persons){
        persons.stream().sorted(Comparator.comparing(Person::getName)).forEach(System.out::println);
    }

    public static Map<Departament, Person> printDepartmentOldestPerson(List<Person> persons){
        AgeComparator comparator = new AgeComparator();

        Map<Departament, Person> map = new HashMap<>();

       persons.stream()
               .collect(Collectors.groupingBy(Person::getDepartament,
                    reducing(BinaryOperator.maxBy(comparator))
               )).entrySet().forEach(x-> {
                   Person max = x.getValue().orElse(null);
                   map.put(x.getKey(), max);
               });
       return map;

    }

    public static List<Person> findFirstPersons(List<Person> persons) {
        return persons.stream()
                .filter(x -> x.getSalary() > 50_000 && Person.getAge(x.getBirthDate()) < 30)
                .limit(10)
                .toList();
    }

    public static Map<Departament, Double> findTopDepartment(List<Person> persons) {

       return persons.stream().collect(Collectors.groupingBy(
                Person::getDepartament,
               summingDouble(Person::getSalary)

        ));

    }

    public static Departament topDepartment(Map<Departament, Double> map){
        return map.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow(IllegalStateException::new).getKey();

    }




}


