package il.ac.huji.todolist;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
    public final static String EXTRA_MESSAGE3 = "il.ac.huji.todolist.MESSAGE3";
    public final static String EXTRA_MESSAGE4 = "il.ac.huji.todolist.MESSAGE4";



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
        //readItems();

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
        //super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(itemsAdapter.getItem(info.position).getTitle());

        String phoneNumber = itemsAdapter.getItem(info.position).getPhone();
        if(!phoneNumber.equals("")){
            menu.add(0,0,0,itemsAdapter.getItem(info.position).getTitle());

        }

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.todo_context_floating_menu, menu);

    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();





        switch (item.getItemId()) {
            case R.id.delete_item:
                itemsAdapter.remove(itemsAdapter.getItem(info.position));
                itemsAdapter.notifyDataSetChanged();
                //writeItems();
                return true;
            case 0:
                String phoneNumber = itemsAdapter.getItem(info.position).getPhone();
                Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dial);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case NEW_ITEM_REQUEST:
                if(resultCode == Activity.RESULT_OK) {
                    String itemT = data.getStringExtra(EXTRA_MESSAGE);
                    String itemDate = data.getStringExtra(EXTRA_MESSAGE2);
                    String isred = data.getStringExtra(EXTRA_MESSAGE3);
                    String phoneNumber = data.getStringExtra(EXTRA_MESSAGE4);

                    if (isred.equals("1")) {
                        itemsAdapter.add(new Item(itemT, itemDate, true, phoneNumber));
                    } else {
                        itemsAdapter.add(new Item(itemT, itemDate, false, phoneNumber));
                    }
                }

               // writeItems();

        }
    }

    public void onAddItem(MenuItem v) {
        Intent intent = new Intent(this, AddNewTodoItemActivity.class);
        startActivityForResult(intent , NEW_ITEM_REQUEST);

        //String itemText = etNewItem.getText().toString();
        //itemsAdapter.add(message);
        //etNewItem.setText("");
        //writeItems();
    }

}
