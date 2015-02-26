package com.example.ggalasso.contactviewer_hw2_bpc;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ggalasso on 2/21/15.
 */
public class ContactManager {

    private Gson gson;
    private String filename = "contact_data.txt";
    private File file;

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
        this.file = new File(Environment.getDataDirectory().getPath().concat(filename));
        this.gson = new Gson();

        if (file.exists()) {
            readContactsFromFile();
        }
        else {
            createContacts();
        }

        //saveContactsToFile(context);

        //this.contactList.add(new Contact
        //)

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
        this.contactList = contactList;
    }


    public void addContact(Contact contact) {
        contactList.add(contact);
        try {
            saveContactsToFile(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addContact() {
        contactList.add(new Contact("Rudy","Reynolds", "Captain", "Mobile","123-456-7890","","m123.hotmail","Twitter","MRey", 10));
        try {
            saveContactsToFile(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Contact> createContacts() {
        contactList.add(new Contact("Malcolm","Reynolds", "Captain", "Mobile","123-456-7890","","m123.hotmail","Twitter","MRey", 1));
        contactList.add(new Contact("Tom", "","Doctor", "Office","123-456-7300","","","","", 2));
        contactList.add(new Contact("Rob","", "Pirate", "Home","123-456-7200","","","","", 3));
        contactList.add(new Contact("Bob", "Doe","Cop", "Mobile", "123-456-7100","","","","", 4));
        contactList.add(new Contact("Tommy","Malone","Engineer","Office", "123-456-7400","","","","", 5));
        return contactList;
    }

    private void saveContactsToFile(Context context) {

        String s = gson.toJson(this.contactList);
        Log.i("ContactManager.java", "Saving Contacts List to File...");
        Log.i("ContactManager.java", s);
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void readContactsFromFile() {

        try {
            //FileInputStream fis = this.context.openFileInput("myfile.txt");
            FileInputStream fis = context.openFileInput(file.getName());
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();

            Type listType = new TypeToken<ArrayList<Contact>>() {
                }.getType();

            //ArrayList<Contact> myList = new Gson().fromJson(json, listType);
            ArrayList<Contact> myList = gson.fromJson(json, listType);

            for (Contact c : myList) {
                Log.i("ContactsManager.java", "Reading Contact from file..."+c.getFirstName());
            }

            setContactList(myList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
