package com.example.address_book.repository;

import android.os.Parcelable;
import com.example.address_book.domain.Contact;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tonata on 8/18/14.
 */
public interface ContactDAO  {

    public void createContact(Contact contact);
    public void updateContact(Contact contact);
    public void deleteContact(Contact contact);
    public int queryForId(Contact contact);
    public Contact getContactById(int id);
    public List<Contact> getContactsList();
    public Contact getContactByDetails(String lastName , String number);
}
