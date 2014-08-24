package com.example.address_book.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;
import com.example.address_book.domain.Contact;
import com.example.address_book.repository.ContactDAO;
import com.example.address_book.repository.DBAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonata on 8/18/14.
 */
public class ContactDAOImpl implements ContactDAO  {

    private SQLiteDatabase database;
    private DBAdapter dbHelper;

    public ContactDAOImpl(Context context){
        dbHelper = new DBAdapter(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void createContact(Contact contact){
        try{


            open();
            ContentValues values = new ContentValues();
            values.put(DBAdapter.COLUMN_FIRSTNAME , contact.getFirstName());
            values.put(DBAdapter.COLUMN_LASTNAME ,  contact.getLastName());
            values.put(DBAdapter.COLUMN_EMAIL ,  contact.getEmail());
            values.put(DBAdapter.COLUMN_CELL,  contact.getCellNumber());
            values.put(DBAdapter.COLUMN_HOME ,  contact.getHomeAddress());

            database.insert(DBAdapter.TABLE_CONTACT, null, values);
            close();
        }
        catch(Exception e){
            Log.d("" , "error: " + e.getMessage());
        }


    }
    public void updateContact(Contact contact){
        try{
            open();
            ContentValues values = new ContentValues();
            values.put(DBAdapter.COLUMN_FIRSTNAME , contact.getFirstName());
            values.put(DBAdapter.COLUMN_LASTNAME , contact.getLastName());
            values.put(DBAdapter.COLUMN_EMAIL , contact.getEmail());
            values.put(DBAdapter.COLUMN_CELL, contact.getCellNumber());
            values.put(DBAdapter.COLUMN_HOME , contact.getHomeAddress());

            database.update(DBAdapter.TABLE_CONTACT, values, DBAdapter.COLUMN_ID + " = ?", new String[]{String.valueOf(contact.getId())});
            close();
        }
        catch(Exception e){

        }

    }
    public void deleteContact(Contact contact){
        try{
            open();
            database.delete(DBAdapter.TABLE_CONTACT , DBAdapter.COLUMN_ID + " = ?" , new String[]{String.valueOf(contact.getId())} );
            close();
        }
        catch(Exception e){

        }
    }
    public Contact getContactById(int id){
        Contact contact  = null;
        try{
            open();
            Cursor cursor = database.query(DBAdapter.TABLE_CONTACT , new String[]{DBAdapter.COLUMN_ID ,
                    DBAdapter.COLUMN_FIRSTNAME ,
                    DBAdapter.COLUMN_LASTNAME ,
                    DBAdapter.COLUMN_EMAIL,
                    DBAdapter.COLUMN_CELL ,
                    DBAdapter.COLUMN_HOME} , DBAdapter.COLUMN_ID + " = ?" , new String[]{String.valueOf(id)} , null , null , null, null);

            if (cursor != null)
                cursor.moveToFirst();

            contact = new Contact.Builder()
                    .id(cursor.getInt(0))
                    .firstName(cursor.getString(1))
                    .lastName(cursor.getString(2))
                    .email(cursor.getString(3))
                    .cellNumber(cursor.getString(4))
                    .homeAddress(cursor.getString(5))
                    .build();
            close();


        }
        catch(Exception e){

        }

        return contact;
    }

    //---- NEEDS EDITING
    public Contact getContactByDetails(String lastName , String number){
        Contact contact = null;
        try{
            open();
            Cursor cursor = database.query(DBAdapter.TABLE_CONTACT, new String[]{DBAdapter.COLUMN_ID ,
                    DBAdapter.COLUMN_FIRSTNAME ,
                    DBAdapter.COLUMN_LASTNAME ,
                    DBAdapter.COLUMN_EMAIL,
                    DBAdapter.COLUMN_CELL ,
                    DBAdapter.COLUMN_HOME} , DBAdapter.COLUMN_LASTNAME + " = ?" + " and " + DBAdapter.COLUMN_CELL + " = ?" , new String[]{String.valueOf(lastName), String.valueOf(number)} , null , null , null, null);

            if (cursor != null)
                cursor.moveToFirst();

           contact = new Contact.Builder()
                   .id(cursor.getInt(0))
                   .firstName(cursor.getString(1))
                   .lastName(cursor.getString(2))
                   .email(cursor.getString(3))
                   .cellNumber(cursor.getString(4))
                   .homeAddress(cursor.getString(5))
                   .build();


            close();


        }
        catch(Exception e){

        }

        return contact;
    }

    public List<Contact> getContactsList(){
        String selectQuery = "SELECT * FROM " + DBAdapter.TABLE_CONTACT;
        List<Contact> cs = new ArrayList<Contact>();

        try{
            open();
            Cursor cursor  = database.rawQuery(selectQuery , null);

            if(cursor.moveToFirst()){
                do{
                    final Contact contact = new Contact.Builder()
                            .id(cursor.getInt(0))
                            .firstName(cursor.getString(1))
                            .lastName(cursor.getString(2))
                            .email(cursor.getString(3))
                            .cellNumber(cursor.getString(4))
                            .homeAddress(cursor.getString(5))
                            .build();
                    cs.add(contact);

                }while(cursor.moveToNext());
            }

            close();
        }
        catch(Exception e){

        }

        return cs;
    }

    //---- NEEDS EDITING
    public int queryForId(Contact contact){
        int columnID = 0;
        try{
            open();
            String name = contact.getLastName();
            String number = contact.getCellNumber();

            //Query for Last name AND Phone number
            Cursor cursor = database.query(DBAdapter.TABLE_CONTACT, new String[]{DBAdapter.COLUMN_ID }, DBAdapter.COLUMN_LASTNAME + " = ?" + " and " + DBAdapter.COLUMN_CELL + " = ?" , new String[]{String.valueOf(name),String.valueOf(number) } , null , null , null, null  );

            if (cursor != null)
                cursor.moveToFirst();


            columnID = cursor.getInt(0);

            close();
        }
        catch(Exception e){

        }

        return columnID;
    }


}
