package com.example.ggalasso.contactviewer_hw2_bpc;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ggalasso on 2/21/15.
 */
public class ContactManager {

    private final String FILENAME = "contact_data.txt";

    private static ContactManager ourInstance = null;
    private Context context;
    private ArrayList<Contact> contactList = new ArrayList<>();

    public static ContactManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ContactManager(context);
        }
        return ourInstance;
    }

    private ContactManager(Context context) {
        this.context = context;
        File f = new File(context.getFilesDir() + "/" + FILENAME);
        //Log.i("ContactManager", "FILE PATH!!!! " + context.getFilesDir());
        //Log.i("ContactManager", "FILE PATH!!!! " + context.getCacheDir());
        if (f.exists()) {
            Log.i("ContactManager", "_________FILE EXISTS_________\n");
            try {
                readGson(context);
                Log.i("READ", "From file");
            } catch (IOException ie) {
                Log.i("EXCEPTION ie", ie.getMessage() + "\n");
            }
        } else {
            Log.i("ContactManager", "++++++ FILE NOT FOUND +++++++ " + f.getPath() + "\n");
            createContacts();
        }
    }

    public Contact getContactById(int id) {
        for (Contact c : getContactList()) {
            if (c.getId() == id) {
                //Log.i("ContactManager", "Found contact ID: " + c.getId() + "\n");
                return c;
            }
        }
        return null;
    }

    public ArrayList<Contact> getContactList() {
        Collections.sort(contactList, Contact.contactComparator);
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }


    public void addContact(Contact contact) {
        getContactList().add(contact);
        try {
            saveGson(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private ArrayList<Contact> createContacts() {
        contactList.add(new Contact("Malcolm", "Reynolds", "Captain", "Mobile", "123-456-7890", "", "m123.hotmail", "Twitter", "MRey", 1));
        contactList.add(new Contact("Tom", "", "Doctor", "Office", "123-456-7300", "", "", "", "", 2));
        contactList.add(new Contact("Rob", "", "Pirate", "Home", "123-456-7200", "", "", "", "", 3));
        contactList.add(new Contact("Bob", "Doe", "Cop", "Mobile", "123-456-7100", "", "", "", "", 4));
        contactList.add(new Contact("Tommy", "Malone", "Engineer", "Office", "123-456-7400", "", "", "", "", 5));
        return contactList;
    }


    public void saveGson(Context ctx) throws IOException {
        FileOutputStream fOut = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        Gson gson = new GsonBuilder().create();
        gson.toJson(getContactList(), myOutWriter);
        myOutWriter.close();
        fOut.close();
    }

    private void readGson(Context ctx) throws IOException {
        try {
            FileInputStream fis = ctx.openFileInput(FILENAME);

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

            ArrayList<Contact> myList = new Gson().fromJson(json, listType);

            for (Contact c : myList) {
                Log.i("Contact:", c.getFirstName() + " ID: " + c.getId());
            }
            setContactList(myList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNextId() {
        Integer nextID = 0;
        for (Contact c : getContactList()) {
            if (c.getId() > nextID) {
                nextID = c.getId();
            }
        }
        return nextID + 1;
    }

    public void deleteContactById(Integer id) {
        Contact c = getContactById(id);
        getContactList().remove(c);
        try {
            saveGson(context);
        } catch (IOException ie) {
            Log.i("ContactManager", "Caught IO exception during delete:" + ie.getMessage());
        }
    }

    public void setContact(String textFirstName, String textLastName, String textTitle,
                           String textPhoneType, String textPhoneNumber, String textEmailType,
                           String textEmailAddress, String textSocialType, String textSocial,
                           Integer contactId) {
        Contact c = getContactById(contactId);
        if(!c.getFirstName().equals(textFirstName)) {c.setFirstName(textFirstName);}
        if(!c.getLastName().equals(textLastName)) {c.setLastName(textLastName);}
        if(!c.getContactTitle().equals(textTitle)) {c.setContactTitle(textTitle);}
        if(!c.getPhoneType().equals(textPhoneType)) {c.setPhoneType(textPhoneType);}
        if(!c.getPhoneNumber().equals(textPhoneNumber)) {c.setPhoneNumber(textPhoneNumber);}
        if(!c.getEmailType().equals(textEmailType)) {c.setEmailType(textEmailType);}
        if(!c.getEmailAdd().equals(textEmailAddress)) {c.setEmailAdd(textEmailAddress);}
        if(!c.getSocialType().equals(textSocialType)) {c.setSocialType(textSocialType);}
        if(!c.getSocial().equals(textSocial)) {c.setSocial(textSocial);}
        try {
            saveGson(this.context);
        } catch (IOException ie) {
            Log.i("ContactManager", "Caught IO exception during edit save:" + ie.getMessage());
        }
    }
}