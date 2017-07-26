package com.course.innopolis.mytaskmanager.models.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cristina on 11.07.2017.
 */

public class MyTaskManagerDBHelper extends SQLiteOpenHelper{
    // Имя файла базы данных
    private static final String DATABASE_NAME = "my_task_manager.db";
    // Версия базы данных. При изменении схемы увеличить на единицу
    private static final int DATABASE_VERSION = 1;

    private static MyTaskManagerDBHelper mInstance;
    private final Context mContext;

    // Создание таблицы users
    //final String SQL_CREATE_USER_TABLE = "CREATE TABLE IF NOT EXIST " + MyTaskManagerContract.UserEntry.TABLE_NAME + " ("
    final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + MyTaskManagerContract.UserEntry.TABLE_NAME + " ("
            + MyTaskManagerContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MyTaskManagerContract.UserEntry.COLUMN_LOGIN + " TEXT NOT NULL, "
            + MyTaskManagerContract.UserEntry.COLUMN_PASSWORD + " TEXT NOT NULL, "
            + MyTaskManagerContract.UserEntry.COLUMN_UNSUCCESSFUL_LOGIN_COUNT + " INTEGER NOT NULL DEFAULT 0, "
            + MyTaskManagerContract.UserEntry.COLUMN_ISACTIVE + " INTEGER NOT NULL DEFAULT 1);";

    // Создание таблицы tasks
    final String SQL_CREATE_TASK_TABLE = "CREATE TABLE " + MyTaskManagerContract.TaskEntry.TABLE_NAME + " ("
            + MyTaskManagerContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MyTaskManagerContract.TaskEntry.COLUMN_NAME + " TEXT NOT NULL, "
            + MyTaskManagerContract.TaskEntry.COLUMN_ISALLDAY + " INTEGER NOT NULL DEFAULT 0, "
            + MyTaskManagerContract.TaskEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + MyTaskManagerContract.TaskEntry.COLUMN_DATEBEG + " DATETIME NOT NULL, "
            + MyTaskManagerContract.TaskEntry.COLUMN_DATEEND  + " DATETIME NOT NULL, "
            + MyTaskManagerContract.TaskEntry.COLUMN_PERIODICITY  + " TEXT NOT NULL, "
            + MyTaskManagerContract.TaskEntry.COLUMN_COLOR + " TEXT NOT NULL, "
            + MyTaskManagerContract.TaskEntry.COLUMN_USER_ID + " TEXT NOT NULL);";

    // Удание таблицы users
    final String SQL_DROP_USER_TABLE = "DROP TABLE IF EXISTS " + MyTaskManagerContract.UserEntry.TABLE_NAME;

    // Удание таблицы tasks
    final String SQL_DROP_TASK_TABLE = "DROP TABLE IF EXISTS " + MyTaskManagerContract.TaskEntry.TABLE_NAME;


    private MyTaskManagerDBHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public static void init (Context mContext) {
        mInstance = new MyTaskManagerDBHelper(mContext);
    }

    public static MyTaskManagerDBHelper getInstance(){
        if (mInstance == null){
            throw new IllegalStateException("DataBase is not instantiated yet. " +
                    "Did you call instantiate() before getInstance()?");
        }
        return mInstance;
    }


    // Вызывается при создании базы данных
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_TASK_TABLE);
    }


    // Вызывается при обновлении схемы базы данных
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DROP_USER_TABLE);
        db.execSQL(SQL_DROP_TASK_TABLE);
        onCreate(db);
    }

}
