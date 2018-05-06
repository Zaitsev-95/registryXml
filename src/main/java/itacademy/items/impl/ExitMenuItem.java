package itacademy.items.impl;

import itacademy.Data;
import itacademy.items.BaseMenuItem;
import itacademy.items.MenuItem;

public class ExitMenuItem extends BaseMenuItem implements MenuItem {
    protected ExitMenuItem(Data data) {
        super(data);
    }

    @Override
    public void execute() {
    }

    @Override
    public String name() {
        return "выхода из программы.";
    }
}