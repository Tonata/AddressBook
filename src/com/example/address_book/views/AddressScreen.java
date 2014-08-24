package com.example.address_book.views;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.address_book.R;
import com.example.address_book.domain.Contact;
import com.example.address_book.repository.ContactDAO;
import com.example.address_book.repository.Impl.ContactDAOImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tonata on 8/18/14.
 */
public class AddressScreen extends Activity {

    private MyCustomAdapter dataAdapter = null;
    private View currentView = null;
    private static final int CONTACT_EDIT = 1;
    ContactDAO dao;
    ListView addressView;
    String action = "";
    ArrayList<Contact> cList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_screen);


        Button add = (Button) findViewById(R.id.btnAdd);
        addressView = (ListView) findViewById(R.id.contactView);
        dao = new ContactDAOImpl(this);
        displayListView();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newContact = new Intent(AddressScreen.this , EditDetails.class);
                startActivity(newContact);
            }
        });

    }

    public void onRestart(){
        super.onRestart();
        displayListView();
    }

   /* protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode) {

            case CONTACT_EDIT:
                if (resultCode == RESULT_OK) {

                    //read the bundle and get the contact object
                    Bundle bundle = data.getExtras();
                    Contact contact = bundle.getParcelable("contact");

                    cList.add(contact);
                    dataAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "New Contact Added" , Toast.LENGTH_SHORT).show();

                }
                break;

        }
    }*/


    public void displayListView(){
        List<Contact> contactList = dao.getContactsList();
        cList = new ArrayList<Contact>();
        cList.addAll(contactList);
        //ArrayList<Contact> emp = new ArrayList<Contact>();

        if(cList.isEmpty()){

            Toast.makeText(AddressScreen.this , "No Items to Display", Toast.LENGTH_SHORT).show();
        }
        else{

               /* ArrayList<Contact> emp = new ArrayList<Contact>();
                emp.addAll(cList);
                for(Contact c: emp){
                    cList.add(c);
                }*/

            dataAdapter = new MyCustomAdapter(AddressScreen.this , R.layout.row_data , cList);
            addressView.setAdapter(dataAdapter);
            dataAdapter.notifyDataSetChanged();
            addressView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Toast.makeText(getApplicationContext() , "Long press on item to retrieve actions menu" ,Toast.LENGTH_LONG ).show();
                }
            });

            addressView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    currentView = view;
                    return false;
                }
            });

            registerForContextMenu(addressView);
        }

    }

    public void onCreateContextMenu(ContextMenu menu , View v ,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu , v , menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;


        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId() , 0 , "View Contact");
       // menu.add(0, v.getId() , 0 , "Edit");
        menu.add(0, v.getId() , 0 , "Remove");

    }

    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if(item.getTitle() == "Edit"){
           /* Contact contact = (Contact) currentView.getTag();

            Intent contactView = new Intent(AddressScreen.this, EditDetails.class);

            Bundle b = new Bundle();
            b.putParcelable("contact", contact);
            b.putString("action" , "edit");
            contactView.putExtras(b);
            startActivity(contactView );*/
        }
        else if(item.getTitle() == "View Contact"){
            Contact contact = (Contact) currentView.getTag();

            Intent contactView = new Intent(AddressScreen.this, DetailsScreen.class);
            Bundle b = new Bundle();
            //pass the contact object as a parcel
            b.putParcelable("contact", contact);
            contactView.putExtras(b);
            startActivity(contactView);

        }
        else if(item.getTitle() == "Remove"){
            Contact contact = (Contact) currentView.getTag();
            dao.deleteContact(contact);
            cList.remove(contact);
            dataAdapter.notifyDataSetChanged();
            displayListView();

        }
        else

        {return false;}
        return true;

    }

    private class MyCustomAdapter extends ArrayAdapter<Contact> {

        private ArrayList<Contact> contactsList;

        public MyCustomAdapter(Context context , int textViewResourceId , ArrayList<Contact> contactList){
            super(context , textViewResourceId , contactList);
            this.contactsList = new ArrayList<Contact>();
            this.contactsList.addAll(contactList);
        }

        public View getView(int position , View convertView , ViewGroup parent){
            TextView lastName = null;
            TextView cell = null;

            if(convertView == null) {
                // View vi = convertView;
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.row_data, parent, false);
                lastName = (TextView) convertView.findViewById(R.id.lastname);
                cell = (TextView) convertView.findViewById(R.id.cell);


                Contact contact = contactsList.get(position);
                contact.setListPosition(position);
                lastName.setText(contact.getLastName());
                cell.setText(contact.getCellNumber());
                convertView.setTag(contact);

                // return convertView;
            }
            return convertView;
        }
    }

}

