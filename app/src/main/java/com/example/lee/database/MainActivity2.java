package com.example.lee.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.lee.database.DBHelper.Category;
import static com.example.lee.database.DBHelper.Frequency;
import static com.example.lee.database.DBHelper.RTNO;
import static com.example.lee.database.DBHelper.Ruletype;


public class MainActivity2 extends ActionBarActivity {

    private DBHelper dbHelper = null;
    private TextView taskname = null;
    private NumberPicker weekpicker = null;
    private NumberPicker daypicker = null;
    private String[] tasktype = {"Health", "Work", "Trivial","Bad Habit"};
    private Button cancelbutton = null;
    private Button deletebutton = null;
    private Button addbutton = null;
    private RadioGroup radiogroup = null;
    private RadioButton oxtype = null;
    private RadioButton numtype = null;
    private Spinner spinner;
    private String spinnerselect;
    int ox,intnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        dbHelper = new DBHelper(this);
        dbHelper.close();
        taskname = (EditText)findViewById(R.id.editText);
        cancelbutton = (Button)findViewById(R.id.button2);
        deletebutton = (Button)findViewById(R.id.button3);
        addbutton = (Button)findViewById(R.id.button4);
        oxtype = (RadioButton)findViewById(R.id.oxradioButton);
        numtype = (RadioButton)findViewById(R.id.intradioButton);
        spinner = (Spinner)findViewById(R.id.spinner);
        weekpicker = (NumberPicker)findViewById(R.id.numberPicker);
        weekpicker.setMinValue(0);
        weekpicker.setMaxValue(7);
        weekpicker.setValue(1);
        daypicker = (NumberPicker)findViewById(R.id.numberPicker2);
        daypicker.setMinValue(0);
        daypicker.setMaxValue(10);
        daypicker.setValue(1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tasktype);



        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(); //新增資料
            }
        });
        spinner.setAdapter(adapter);
        //設定項目被選取之後的動作
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                spinnerselect = spinner.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView arg0) {

            }
        });
        RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.oxradioButton:
                        ox = 1;
                        intnum = 0;
                        break;
                    case R.id.intradioButton:
                        ox = 0;
                        intnum = 1;
                        break;
                }
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
    private void add(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        int intweek = weekpicker.getValue();
        int intday = daypicker.getValue();
        values.put(RTNO, taskname.getText().toString());
        values.put("Times",intday);
        //values.put(Category, column2Text.getText().toString());
        //values.put(Frequency, column3Text.getText().toString());

        if(ox ==1 && intweek==1){
            values.put(Ruletype, "RT1");
        }
        else if(intnum==1 && intweek==1){
            values.put(Ruletype,"RT2");
        }
        else if(ox == 1 && intweek>1){
            values.put(Ruletype,"RT3");
        }
        else if(intnum == 1 && intweek>1){
            values.put(Ruletype,"RT4");
        }



        db.insert("Task", null, values);
    }

}
