package com.example.lee.database;

import static android.provider.BaseColumns._ID;
//呼叫HELP類的變數
import static com.example.lee.database.DBHelper.Frequency;
import static com.example.lee.database.DBHelper.Category;
import static com.example.lee.database.DBHelper.Ruletype;
import static com.example.lee.database.DBHelper.TaskNo;
import static com.example.lee.database.DBHelper.setRemindTime_PD;
import static com.example.lee.database.DBHelper.RTNO;
import static com.example.lee.database.DBHelper.RemindTime1;
import static com.example.lee.database.DBHelper.RemindTime2;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.FeatureGroupInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

    private DBHelper dbHelper = null;
    private TextView result = null;
    private Button addbutton = null;
    private Button querybutton = null;
    private EditText column1Text = null;
    private EditText column2Text = null;
    private EditText column3Text = null;
    private Spinner spinner;
    private String[] dbselectlist = {"Ruletype", "Task", "setRemindTime_PD"};
    private String dbselect;

    private GestureDetector mGestureDetector;
    private int verticalMinDistance = 180;
    private int minVelocity         = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //執行help類，會建立demo資料庫和三個欄位
        dbHelper = new DBHelper(this);
        dbHelper.close();
        column1Text = (EditText)findViewById(R.id.editText1);
        column2Text = (EditText)findViewById(R.id.editText2);
        column3Text = (EditText)findViewById(R.id.editText3);
        addbutton = (Button)findViewById(R.id.addbutton);
        querybutton = (Button)findViewById(R.id.button);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dbselectlist);


        addbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add(); //新增資料
            }
        });
        querybutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
                column1Text.setText("");
                column2Text.setText("");
                column3Text.setText("");
            }
        });
        result = (TextView) findViewById(R.id.textview);
        spinner.setAdapter(adapter);
                //設定項目被選取之後的動作
                spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
                    public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                                dbselect = spinner.getSelectedItem().toString();
                            }
                        public void onNothingSelected(AdapterView arg0) {

                            }
                    });
    }
    //新增資料
    private void add(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(dbselect == "Ruletype") {
            values.put(RTNO, column1Text.getText().toString());
            values.put(Category, column2Text.getText().toString());
            values.put(Frequency, column3Text.getText().toString());
            db.insert(Ruletype, null, values);
        }
        if(dbselect == setRemindTime_PD) {
            values.put(TaskNo, column1Text.getText().toString());
            values.put(RemindTime1, column2Text.getText().toString());
            values.put(RemindTime2, column3Text.getText().toString());
            db.insert(setRemindTime_PD, null, values);
        }
    }
    //顯示資料
    public void show(){
        Cursor cursor = getCursor();
        StringBuilder resultData = new StringBuilder("RESULT: \n");
        if(dbselect == setRemindTime_PD) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String column1 = cursor.getString(1);
                String column2 = cursor.getString(2);
                String column3 = cursor.getString(3);
                String column4 = cursor.getString(4);
                String column5 = cursor.getString(5);


                resultData.append(id).append(": ");
                resultData.append(column1).append(", ");
                resultData.append(column2).append(", ");
                resultData.append(column3).append(", ");
                resultData.append(column4).append(", ");
                resultData.append(column5).append(", ");

                resultData.append("\n");
            }
        }
        if(dbselect == "Ruletype") {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String column1 = cursor.getString(1);
                String column2 = cursor.getString(2);
                String column3 = cursor.getString(3);



                resultData.append(id).append(": ");
                resultData.append(column1).append(", ");
                resultData.append(column2).append(", ");
                resultData.append(column3).append(", ");


                resultData.append("\n");
            }
        }

        result.setText(resultData);
    }

    public Cursor getCursor(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //String[] columns = {_ID, RTNO, Category, Frequency};
        Cursor cursor = null;
        if(dbselect == setRemindTime_PD) {
            String[] columns = {_ID, "TaskNo", "RemindTime1", "RemindTime2", "RemindTime3", "RemindTime4", "RemindTime5"};
            cursor = db.query(setRemindTime_PD, columns, null, null, null, null, null);
        }
        if(dbselect == "Ruletype"){
            String[] columns = {_ID, RTNO, Category, Frequency};
            cursor = db.query("Ruletype", columns, null, null, null, null, null);
        }
        startManagingCursor(cursor);
        return cursor;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    private void initGesture() {
        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {

            // 切换Activity
            // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
            // startActivity(intent);
            //Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
        } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {

            // 切换Activity
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
            finish();
            //overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }

        return false;
    }


    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }


    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                            float arg3) {
        // TODO Auto-generated method stub
        return false;
    }


    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }


    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }


    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        return mGestureDetector.onTouchEvent(event);
    }


    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        try
        {
            mGestureDetector.onTouchEvent(ev);
        }
        catch (Exception e)
        {
        }
        return super.dispatchTouchEvent(ev);


    }
}
