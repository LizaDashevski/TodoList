package il.ac.huji.todolist;

/**
 * Created by Liza on 3/19/16.
 */

public class Item {

    private String title;
    private String description;
    private boolean color;
    private String phone;

    public Item(String title, String description , boolean color , String phone) {
        super();
        this.title = title;
        this.description = description;
        this.color = color;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }
}

