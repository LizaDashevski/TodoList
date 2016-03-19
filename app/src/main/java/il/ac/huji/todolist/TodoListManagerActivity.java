package il.ac.huji.todolist;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TodoListManagerActivity extends AppCompatActivity {
    static final int NEW_ITEM_REQUEST = 1;
    public final static String EXTRA_MESSAGE = "il.ac.huji.todolist.MESSAGE";
    public final static String EXTRA_MESSAGE2 = "il.ac.huji.todolist.MESSAGE2";

    private ArrayList<Item> items;
    private SpecialAdapter itemsAdapter;
    private ListView lvItems;

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<Item>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<Item>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<Item>();
        readItems();

        itemsAdapter = new SpecialAdapter(this , R.layout.row, items);

        lvItems.setAdapter(itemsAdapter);
        registerForContextMenu(lvItems);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_list_manager, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(itemsAdapter.getItem(info.position).toString());


        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.todo_context_floating_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        itemsAdapter.remove(itemsAdapter.getItem(info.position)); //Why not deleting???
        itemsAdapter.notifyDataSetChanged();
        writeItems();

        return super.onContextItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case NEW_ITEM_REQUEST:
                String itemT = data.getStringExtra(EXTRA_MESSAGE);
                String itemDate = data.getStringExtra(EXTRA_MESSAGE2);
                itemsAdapter.add(new Item(itemT , itemDate));

                writeItems();




        }
    }

    public void onAddItem(MenuItem v) {
        Intent intent = new Intent(this, AddNewTodoItemActivity.class);


        EditText etNewItem = (EditText) findViewById(R.id.edtNewItem);
        //String message = etNewItem.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivityForResult(intent, NEW_ITEM_REQUEST);
        startActivityForResult(intent , NEW_ITEM_REQUEST);

        //String itemText = etNewItem.getText().toString();
        //itemsAdapter.add(message);
        //etNewItem.setText("");
        //writeItems();
    }



}
