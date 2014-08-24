package com.example.address_book.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.address_book.R;

/**
 * Created by tonata on 8/18/14.
 */
public class FirstScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        Button proceed = (Button) findViewById(R.id.btnProceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstScreen.this , AddressScreen.class);
                startActivity(intent);
            }
        });
    }
}