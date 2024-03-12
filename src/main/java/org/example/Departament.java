package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Departament {
    private String name;

    public static List<Departament> generateDepartaments(int size) {

        List<Departament> departaments = new ArrayList<>();

        for (int i = 1; i < size+1; i++) {
            departaments.add(new Departament("Departament #" + i));
        }

        return departaments;
    }
}
