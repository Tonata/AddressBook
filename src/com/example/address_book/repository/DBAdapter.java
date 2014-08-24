package com.example.address_book.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tonata on 8/20/14.
 */
public class DBAdapter extends SQLiteOpenHelper{

    public static final String TABLE_CONTACT = "contacts";


    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CELL = "cell";
    public static final String COLUMN_HOME = "home";

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_CONTACT_TABLE = " CREATE TABLE IF NOT EXISTS "
            + TABLE_CONTACT + " ( "
            + COLUMN_ID + " integer primary key autoincrement , "
            + COLUMN_FIRSTNAME + " text not null , "
            + COLUMN_LASTNAME + " text not null , "
            + COLUMN_EMAIL + " text , "
            + COLUMN_CELL + " text not null , "
            + COLUMN_HOME + " text not null ) ; ";



    public DBAdapter ( Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("", "database");
    }

    @Override
    public void onCreate ( SQLiteDatabase database ) {

        database.execSQL ( CREATE_CONTACT_TABLE) ;

    }

    @Override
    public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion ) {
        Log.w(DBAdapter.class.getName(),
                "Upgrading database from version " + oldVersion + "to"
                        +newVersion +" ,which will destroy all old data");

        db.execSQL( " DROP TABLE IF EXISTS " + TABLE_CONTACT);


        onCreate(db);
    }
}
