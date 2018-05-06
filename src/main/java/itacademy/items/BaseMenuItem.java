package itacademy.items;

import itacademy.Data;

public abstract class BaseMenuItem implements MenuItem {
    private final Data data;

    protected BaseMenuItem(Data data) {
        this.data = data;
    }

    public abstract String name();

    protected Data data() {
        return data;
    }
}