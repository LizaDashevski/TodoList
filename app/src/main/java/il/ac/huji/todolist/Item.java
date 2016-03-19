package il.ac.huji.todolist;

/**
 * Created by Liza on 3/19/16.
 */

public class Item {

    private String title;
    private String description;

    public Item(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }
}