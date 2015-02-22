package com.example.ggalasso.contactviewer_hw2_bpc;

import android.app.Activity;
import android.content.Intent;
//import android.nfc.Tag;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
import android.widget.TextView;
//import android.widget.Toast;


public class ContactDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

       Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();
        if (!(extrasBundle == null) && !(extrasBundle.isEmpty())) {
            int id = extrasBundle.getInt("id");
            ContactManager cm = ContactManager.getInstance(this);
            Contact contact = cm.getContactById(id);

            ((TextView) findViewById(R.id.contact_details_name)).setText(contact.getName());
            ((TextView) findViewById(R.id.contact_details_title)).setText(contact.getTitle());
            ((TextView) findViewById(R.id.contact_details_phone)).setText(contact.getPhone());

            Log.i("ContactDetails", "ID: " + id + " Name: " + contact.getName() + " Title: " + contact.getTitle()+ " Phone: " + contact.getPhone());

            //display the up/back icons on ActionBar
            getActionBar().setDisplayHomeAsUpEnabled(true);


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_edit:
                //openEdit();
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
