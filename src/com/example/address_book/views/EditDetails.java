package com.example.address_book.views;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.address_book.R;
import com.example.address_book.domain.Contact;
import com.example.address_book.repository.ContactDAO;
import com.example.address_book.repository.Impl.ContactDAOImpl;

import java.util.ArrayList;

/**
 * Created by tonata on 8/18/14.
 */
public class EditDetails extends Activity {

    Context ctx;
    ContactDAO dao;
    String action = "";
    ActionBar actionBar;
    Contact oldContact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_details);
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        dao = new ContactDAOImpl(this);

        final EditText fName = (EditText) findViewById(R.id.txtName);
        final EditText lName = (EditText) findViewById(R.id.txtSurname);
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        final EditText cell = (EditText) findViewById(R.id.txtCell);
        final EditText home = (EditText) findViewById(R.id.txtAddress);

        ActionBar.Tab moviesTab = actionBar.newTab().setText("Movies");
        ActionBar.Tab movieTab = actionBar.newTab().setText("Movies");
        ActionBar.Tab mTab = actionBar.newTab().setText("Movies");

        movieTab.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });
        moviesTab.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });
        mTab.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });
        actionBar.addTab(moviesTab);
        actionBar.addTab(movieTab);
        actionBar.addTab(mTab);


        ctx = getApplicationContext();
        Button saveBtn = (Button) findViewById(R.id.btnSave);
        Button reset = (Button) findViewById(R.id.btnReset);
        Button back = (Button) findViewById(R.id.bBack);

        //Back Button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Reset Button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fName.getText().clear();
                lName.getText().clear();
                cell.getText().clear();
                home.getText().clear();
                email.getText().clear();
            }
        });

        // Save Button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                        if((fName.getText().toString().equals("")) || (lName.getText().toString().equals(""))||
                                ( cell.getText().toString().equals("")) ||(home.getText().toString().equals(""))){
                            Toast.makeText(getApplicationContext() , "Some fields have been left empty. Please fill in all fields." , Toast.LENGTH_LONG).show();

                        }
                        else{
                            Contact contact = new Contact.Builder()
                                    .firstName(fName.getText().toString())
                                    .lastName(lName.getText().toString())
                                    .cellNumber(cell.getText().toString())
                                    .homeAddress(home.getText().toString())
                                    .email(email.getText().toString())
                                    .build();

                            dao.createContact(contact);
                            finish();

                        }

                        //Toast.makeText(getApplicationContext() , "Contact Added Successfully" , Toast.LENGTH_SHORT ).show();


                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext() , "Error occured while adding contact. " + e.getMessage(), Toast.LENGTH_SHORT ).show();
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId()) {

            case R.id.menu_search:
                //search
                return true;

            case R.id.log_out:
                finish();
                return true;

            case R.id.menu_refresh:
                //refresh
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}