package com.example.ggalasso.contactviewer_hw2_bpc;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ggalasso on 2/21/15.
 */
public class ContactManager {
    //private static ContactManager ourInstance = new ContactManager();
    private static ContactManager ourInstance = null;
    private Context context;
    private ArrayList<Contact> contactList = new ArrayList<Contact>();

    public static ContactManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ContactManager(context);
        }
        return ourInstance;
    }

    private ContactManager(Context context) {
        this.context = context;
        createContacts();
    }

    public Contact getContactById(int id) {
        ArrayList<Contact> myContactList = getContactList();
        Iterator<Contact> itr = myContactList.iterator();
        while (itr.hasNext()) {
            Contact c = itr.next();
            if(c.getId() == id) {
                //Log.i("ContactManager", "Found contact ID: " + c.getId() + "\n");
                return c;
            }
        }
        return null;
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = this.contactList;
    }

    private ArrayList<Contact> createContacts() {
        contactList.add(new Contact("Malcolm","Reynolds", "Captain", "Mobile","123-456-7890","","m123.hotmail","Twitter","MRey", 1));
        contactList.add(new Contact("Tom", "","Doctor", "Office","123-456-7300","","","","", 2));
        contactList.add(new Contact("Rob","", "Pirate", "Home","123-456-7200","","","","", 3));
        contactList.add(new Contact("Bob", "Doe","Cop", "Mobile", "123-456-7100","","","","", 4));
        contactList.add(new Contact("Tommy","Malone","Engineer","Office", "123-456-7400","","","","", 5));
        return contactList;
    }

}
