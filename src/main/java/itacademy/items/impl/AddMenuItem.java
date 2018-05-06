package itacademy.items.impl;

import itacademy.*;
import itacademy.items.GeneralBaseMenuItem;
import itacademy.items.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class AddMenuItem extends GeneralBaseMenuItem implements MenuItem {
    GeneralBase generalBase = new GeneralBasePatients();
    OfflineBase offlineBase = new OfflineBase();
    private int quantityPatients = 0;
    private Set<Patient> listPatients = new LinkedHashSet<>();
    private Patient patient;

    public AddMenuItem(Data data, MenuItem menuItem) {
        super(data, menuItem);
    }

    @Override
    public String name() {
        return "внесения новых пациентов";
    }

    @Override
    public void baseExecute() {
        for (Patient p : addNewPatients()) {
            data().add(p);
        }
        System.out.println("...данные успешно добавлены\n");
        for (Patient patient : generalBase.getGeneralBase()) {
            data().add(patient);
        }
        offlineBase.writeInfo(data().provideList());
    }

    private Set<Patient> addNewPatients() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println("Введите сколько пациентов вы хотите добавить:");
        Scanner quantity = new Scanner(System.in);
        quantityPatients = quantity.nextInt();
        for (int i = 0; i < quantityPatients; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите имя");
            String name = scanner.nextLine();
            System.out.println("Введите фамилию");
            String secondName = scanner.nextLine();
            System.out.println("Введите дату рождения в формате 'dd.MM.yyyy'");
            String birthday = scanner.nextLine();
            System.out.println("true/false");
            boolean healthy = scanner.nextBoolean();
            Date date = null;
            try {
                date = sdf.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            patient = new Patient(name, secondName, date, healthy);
            listPatients.remove(patient);
            listPatients.add(patient);
        }
        return listPatients;
    }
}