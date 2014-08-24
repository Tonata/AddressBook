package com.example.address_book.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.address_book.R;
import com.example.address_book.domain.Contact;

/**
 * Created by tonata on 8/18/14.
 */
public class DetailsScreen extends Activity {
    private Contact contact;
    private EditText fName;
    private EditText lName;
    private EditText cell;
    private EditText home;
    private EditText email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_screen);
        Button back = (Button) findViewById(R.id.btnBack);

        Bundle bundle = this.getIntent().getExtras();
        contact = bundle.getParcelable("contact");

        fName = (EditText) findViewById(R.id.viewName);
        lName = (EditText) findViewById(R.id.viewLast);
        cell = (EditText) findViewById(R.id.tCell);
        home = (EditText) findViewById(R.id.tHome);
        email = (EditText) findViewById(R.id.tEmail);

        fName.setText(contact.getFirstName());
        lName.setText(contact.getLastName());
        cell.setText(contact.getCellNumber());
        home.setText(contact.getHomeAddress());
        email.setText(contact.getEmail());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}