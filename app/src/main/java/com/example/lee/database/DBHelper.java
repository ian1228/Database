package com.example.lee.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

//到此一遊
public class DBHelper extends SQLiteOpenHelper {
    //宣告公用常數(final)
    public static final String Ruletype = "Ruletype";//表格名稱
    public static final String RTNO = "RTno";
    public static final String Category ="Category";
    public static final String Frequency ="Frequency";
    public static final String TaskNo ="TaskNo";
    public static final String Name ="Name";
    public static final String Times ="Times";
    public static final String StartDate ="StartDate";
    public static final String EndDate ="EndDate";
    public static final String Description ="Description";
    public static final String setRemindTime ="setRemindTime";
    public static final String RuleType ="RuleType";
    public static final String RemindTime1 ="RemindTime1";
    public static final String RemindTime2 ="RemindTime2";
    public static final String RemindTime3 ="RemindTime3";
    public static final String RemindTime4 ="RemindTime4";
    public static final String RemindTime5 ="RemindTime5";
    public static final String setRemindTime_PD ="setRemindTime_PD";
    public static final String setRemindTime_PW ="setRemindTime_PW";
    public static final String Task ="Task";
    public static final String Date ="Date";
    public static final String Time ="Time";
    public static final String Records ="Records";
    public static final String CheckTimes ="CheckTimes";
    public static final String RT1_Records ="RT1_Records";




    private final static String DATABASE_NAME = "demo.db"; //資料庫名稱
    private final static int DATABASE_VERSION  = 1; //資料庫版本
    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //建立table,有 NAME,TEL,EMAIL三個欄位
    public void onCreate(SQLiteDatabase db){
        final String INIT_RULETYPE = "CREATE TABLE " + Ruletype + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "RTNO TEXT, "+
                "Category TEXT, "+
                "Frequency TEXT"+")";
        db.execSQL(INIT_RULETYPE);
        final String INIT_TASK = "CREATE TABLE " + "Task" + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TaskNo+"TEXT, "+
                Name +"TEXT, "+
                Times+" INTEGER,"+
                StartDate+" TEXT,"+
                EndDate +"TEXT,"+
                Description+" TEXT,"+
                setRemindTime+" TEXT,"+
                RuleType +" TEXT,"+
                "TaskType + INTEGER"+
                ")";
        db.execSQL(INIT_TASK);
        final String INIT_setRemindTime_PD = "CREATE TABLE " + setRemindTime_PD + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TaskNo+" TEXT, "+
                RemindTime1+" TEXT, "+
                RemindTime2+" TEXT," +
                RemindTime3+" TEXT,"+
                RemindTime4+" TEXT,"+
                RemindTime5+" TEXT"+
                ")";
        db.execSQL(INIT_setRemindTime_PD);
        final String INIT_setRemindTime_PW = "CREATE TABLE " + setRemindTime_PW + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TaskNo+" TEXT, "+
                RemindTime1+" TEXT, "+
                RemindTime2+" TEXT," +
                RemindTime3+" TEXT,"+
                RemindTime4+" TEXT,"+
                RemindTime5+" TEXT"+
                ")";
        db.execSQL(INIT_setRemindTime_PW);
        final String INIT_RT1_Records = "CREATE TABLE " + RT1_Records + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Task+ "TEXT, "+
                Date+ "TEXT, "+
                Time+ "TEXT, " +
                Records+ "INTEGER,"+
                CheckTimes+ "INTEGER"+
                ")";
        db.execSQL(INIT_RT1_Records);
        final String INIT_RT2_Records = "CREATE TABLE " + "RT2_Records" + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Task TEXT, "+
                "Date TEXT, "+
                "Time TEXT," +
                "Records REAL,"+
                "CheckTimes INTEGER"+
                ")";
        db.execSQL(INIT_RT2_Records);
        final String INIT_RT3_Records = "CREATE TABLE " + "RT3_Records" + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Task TEXT, "+
                "Date TEXT, "+
                "Time TEXT," +
                "Records INTEGER,"+
                "CheckTimes INTEGER"+
                ")";
        db.execSQL(INIT_RT3_Records);
        final String INIT_RT4_Records = "CREATE TABLE " + "RT4_Records" + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Task TEXT, "+
                "Date TEXT, "+
                "Time TEXT," +
                "Records REAL,"+
                "CheckTimes INTEGER"+
                ")";
        db.execSQL(INIT_RT4_Records);
        final String INIT_RT1_Completed = "CREATE TABLE " + "RT1_Completed" + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Task TEXT, "+
                "Date TEXT, "+
                "Completed INTEGER" +
                ")";
        db.execSQL(INIT_RT1_Completed);
        final String INIT_RT2_Completed = "CREATE TABLE " + "RT2_Completed" + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Task TEXT, "+
                "Date TEXT, "+
                "Completed INTEGER" +
                ")";
        db.execSQL(INIT_RT2_Completed);
        final String INIT_RT3_Completed = "CREATE TABLE " + "RT3_Completed" + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Task TEXT, "+
                "Date TEXT, "+
                "Completed INTEGER" +
                ")";
        db.execSQL(INIT_RT3_Completed);
        final String INIT_RT4_Completed = "CREATE TABLE " + "RT4_Completed" + "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Task TEXT, "+
                "Date TEXT, "+
                "Completed INTEGER" +
                ")";
        db.execSQL(INIT_RT4_Completed);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

        if (newVersion > oldVersion){

        }

        //final String DROP_TABLE = "DROP TABLE IF EXISTS" + Ruletype;
        //db.execSQL(DROP_TABLE);
        //db.execSQL("drop table if exists ");
        //onCreate(db);
    }
}
