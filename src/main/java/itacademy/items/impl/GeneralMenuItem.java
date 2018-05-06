package itacademy.items.impl;

import itacademy.Data;
import itacademy.items.BaseMenuItem;
import itacademy.items.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GeneralMenuItem implements MenuItem {
    private Data data = new Data();
    private Map<Integer, BaseMenuItem> items = new HashMap<>();

    {
        items.put(1, new ListMenuItem(data, this));
        items.put(2, new AddMenuItem(data, this));
        items.put(3, new ExitMenuItem(data));
    }

    @Override
    public void execute() {
        for (Map.Entry<Integer, BaseMenuItem> entry : items.entrySet()) {
            System.out.println("Введите " + entry.getKey() + " для " + entry.getValue().name());
        }
        Scanner scanner = new Scanner(System.in);
        items.get(scanner.nextInt()).execute();
        scanner.nextLine();
    }
}