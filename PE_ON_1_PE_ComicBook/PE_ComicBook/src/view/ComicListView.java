package view;

import java.util.ArrayList;

public abstract class ComicListView<T> {
    private String title;
    protected ArrayList<T> options;
    private boolean isStop;

    public ComicListView() {
        this.isStop = false;
        this.options = new ArrayList<>();
    }

    public ComicListView(String title, T[] options) {
        this.title = title;
        this.options = new ArrayList<>();

        for (T option : options) {
            this.options.add(option);
        }
        this.isStop = false;
    }

    public void display() {
        System.out.println(this.title);
        System.out.println("--------------------------------");

        for (int i = 0; i < this.options.size(); i++) {
            System.out.println((i + 1) + ". " + this.options.get(i));
        }

        System.out.println("--------------------------------");
    }

    public int getSelected() {
        this.display();
        return Validation.getInt("Enter selection: ", 1, this.options.size());
    }

    public abstract void execute(int choice);

    protected void stop() {
        this.isStop = true;
    }

    public void run() {
        while (!this.isStop) {
            int choice = getSelected();
            this.execute(choice);
        }
    }
}