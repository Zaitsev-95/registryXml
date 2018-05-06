package itacademy;

import java.util.LinkedHashSet;
import java.util.Set;

public class Data {
    private Set<Patient> list = new LinkedHashSet<>();
    GeneralBase generalBase = new GeneralBasePatients();

    public void list() {//показать
        list = generalBase.getGeneralBase();
        for (Patient patient : list) {
            System.out.println(patient);
        }
    }

    public Set<Patient> provideList() {
        return list;
    }

    public void add(Patient patient) {//добавить
        list.add(patient);
    }
}