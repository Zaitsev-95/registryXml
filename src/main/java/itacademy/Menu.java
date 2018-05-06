package itacademy;

import itacademy.items.MenuItem;
import itacademy.items.impl.GeneralMenuItem;

public class Menu {
    private MenuItem startMenu = new GeneralMenuItem();

    public void start() {
        startMenu.execute();
    }
}