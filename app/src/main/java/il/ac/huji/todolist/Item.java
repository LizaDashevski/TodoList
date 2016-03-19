package il.ac.huji.todolist;

/**
 * Created by Liza on 3/19/16.
 */

public class Item {

    private String title;
    private String description;
    private boolean color;

    public Item(String title, String description , boolean color) {
        super();
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean col) {
        this.color = col;
    }
}

