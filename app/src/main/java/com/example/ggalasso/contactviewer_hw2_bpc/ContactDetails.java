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
    int contactId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

       Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();
        if (!(extrasBundle == null) && !(extrasBundle.isEmpty())) {
            int id = extrasBundle.getInt("id");
            contactId = id;
            ContactManager cm = ContactManager.getInstance(this);
            Contact contact = cm.getContactById(id);

            ((TextView) findViewById(R.id.contact_details_first_name_field)).setText(contact.getFirstName());
            ((TextView) findViewById(R.id.contact_details_last_name_field)).setText(contact.getLastName());
            ((TextView) findViewById(R.id.contact_details_title_field)).setText(contact.getContactTitle());
            ((TextView) findViewById(R.id.contact_details_phone_field)).setText(contact.getPhoneType());
            ((TextView) findViewById(R.id.contact_details_phone_field2)).setText(contact.getPhoneNumber());
            ((TextView) findViewById(R.id.contact_details_email_field)).setText(contact.getEmailType());
            ((TextView) findViewById(R.id.contact_details_email_field2)).setText(contact.getEmailAdd());
            ((TextView) findViewById(R.id.contact_details_social_field)).setText(contact.getSocialType());
            ((TextView) findViewById(R.id.contact_details_social_field2)).setText(contact.getSocial());

            Log.i("ContactDetails", "ID: " + id +
                    " First Name: " + contact.getFirstName() +
                    " Last Name: " + contact.getLastName() +
                    " Title: " + contact.getContactTitle() +
                    " Phone: " + contact.getPhoneNumber());

            //display the up/back icons on ActionBar
            //getActionBar().setDisplayHomeAsUpEnabled(true);


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
                openEdit();
                return true;
            case R.id.action_remove:
                deleteContact();
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void openEdit(){

        ContactManager cm = ContactManager.getInstance(this);
        Contact contact = cm.getContactById(contactId);
        Bundle contactInfo = new Bundle();
        contactInfo.putInt("id", contact.getId());

        Intent intent = new Intent(this, EditContact.class);
        intent.putExtras(contactInfo);
        startActivity(intent);
    }

    private void deleteContact() {
        //Intent intentExtras = getIntent();
        //Bundle extrasBundle = intentExtras.getExtras();
        //if (!(extrasBundle == null) && !(extrasBundle.isEmpty())) {
        //    int id = extrasBundle.getInt("id");
        //    contactId = id;
            ContactManager cm = ContactManager.getInstance(this);
            cm.deleteContactById(contactId);
            Log.i("ContactDetails.java", "DELETE TRY!!!!" + contactId + " deleted!\n");
        //}
        finish();
    }

}
