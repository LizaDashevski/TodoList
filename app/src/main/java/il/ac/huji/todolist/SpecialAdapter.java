package il.ac.huji.todolist;

/**
 * Created by Liza on 3/12/16.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SpecialAdapter extends ArrayAdapter<Item> {
    final Context c;
    private ArrayList<Item> items;
    private int[] colors = new int[] { 0x30FF0000, 0x300000FF };

    public SpecialAdapter(Context context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);

        this.c = context;
        this.items = objects;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // 3. Get the two text view from the rowView
        TextView valueView = (TextView) rowView.findViewById(R.id.txtTodoTitle);
        TextView dateView = (TextView) rowView.findViewById(R.id.txtTodoDueDate);


        // 4. Set the text for textView
        valueView.setText(items.get(position).getTitle());
        dateView.setText(items.get(position).getDescription());




        //View view = super.getView(position, convertView, parent);
        int colorPos = position % colors.length;
        rowView.setBackgroundColor(colors[colorPos]);
        return rowView;
    }



}


