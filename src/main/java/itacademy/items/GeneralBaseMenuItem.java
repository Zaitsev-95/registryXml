package itacademy.items;

import itacademy.Data;

public abstract class GeneralBaseMenuItem extends BaseMenuItem {
    private final MenuItem startMenuItem;

    protected GeneralBaseMenuItem(Data data, MenuItem startMenuItem) {
        super(data);
        this.startMenuItem = startMenuItem;
    }

    protected MenuItem startMenuItem() {
        return startMenuItem;
    }

    @Override
    public void execute() {
        baseExecute();
        startMenuItem().execute();
    }

    protected abstract void baseExecute();

    @Override
    public abstract String name();
}