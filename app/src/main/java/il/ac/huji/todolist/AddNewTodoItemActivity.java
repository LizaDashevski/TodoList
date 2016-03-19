package il.ac.huji.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNewTodoItemActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "il.ac.huji.todolist.MESSAGE";
    public final static String EXTRA_MESSAGE2 = "il.ac.huji.todolist.MESSAGE2";
    public final static String EXTRA_MESSAGE3 = "il.ac.huji.todolist.MESSAGE3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo_item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_todo_item, menu);
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


    public void addItem(View v) throws ParseException {
        EditText etNewItem = (EditText) findViewById(R.id.edtNewItem);
        String message = etNewItem.getText().toString();

        DatePicker date = (DatePicker) findViewById(R.id.datePicker);
        Integer dobYear = date.getYear();
        Integer dobMonth = date.getMonth() + 1;
        Integer dobDate = date.getDayOfMonth();
        StringBuilder sb = new StringBuilder();
        sb.append(dobYear.toString()).append("-").append(dobMonth.toString()).append("-").append(dobDate.toString());
        String dobStr = sb.toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(dobStr);


        Intent result = new Intent();
        result.putExtra(EXTRA_MESSAGE, message);
        result.putExtra(EXTRA_MESSAGE2, dobStr);
        Calendar now = Calendar.getInstance();
        int res = now.getTime().compareTo(d);
        if (res>=0) {
            result.putExtra(EXTRA_MESSAGE3, "");
        } else {
            result.putExtra(EXTRA_MESSAGE3, "1");

        }

        setResult(RESULT_OK, result);
        finish();
    }
}


