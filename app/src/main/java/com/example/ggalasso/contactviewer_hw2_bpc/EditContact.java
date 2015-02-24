package com.example.ggalasso.contactviewer_hw2_bpc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class EditContact extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String urlStr = "http://contacts.tinyapollo.com/contacts?key=totally";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        InputStream source = retrieveStream(urlStr);
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(source);
        Contact response = gson.fromJson(reader, Contact.class);
        Toast.makeText(this, response.getFirstName(), Toast.LENGTH_SHORT).show();
        String name = response.getFirstName();
        String email = response.getEmailAdd();
        String phone = response.getPhoneNumber();


        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();
        if (!(extrasBundle == null) && !(extrasBundle.isEmpty())) {
            int id = extrasBundle.getInt("id");

            ContactManager cm = ContactManager.getInstance(this);
            Contact contact = cm.getContactById(id);

            ((TextView) findViewById(R.id.first_name_field)).setText(contact.getFirstName());
            ((TextView) findViewById(R.id.last_name_field)).setText(contact.getLastName());
            ((TextView) findViewById(R.id.title_field)).setText(contact.getContactTitle());
            ((TextView) findViewById(R.id.phone_field)).setText(contact.getPhoneType());
            ((TextView) findViewById(R.id.phone_field2)).setText(contact.getPhoneNumber());
            ((TextView) findViewById(R.id.email_field)).setText(contact.getEmailType());
            ((TextView) findViewById(R.id.email_field2)).setText(contact.getEmailAdd());
            ((TextView) findViewById(R.id.social_field)).setText(contact.getSocialType());
            ((TextView) findViewById(R.id.social_field2)).setText(contact.getSocial());

            Log.i("Edit Contact", "ID: " + id +
                    " First Name: " + contact.getFirstName() +
                    " Last Name: " + contact.getLastName() +
                    " Title: " + contact.getContactTitle() +
                    " Phone: " + contact.getPhoneNumber());


        }


        final EditText editFirstName = (EditText) findViewById(R.id.first_name_field);
        final EditText editLastName = (EditText) findViewById(R.id.last_name_field);
        final EditText editTitle = (EditText) findViewById(R.id.title_field);
        final EditText editPhoneType = (EditText) findViewById(R.id.phone_field);
        final EditText editPhoneNumber = (EditText) findViewById(R.id.phone_field2);
        final EditText editEmailType = (EditText) findViewById(R.id.email_field);
        final EditText editEmailAddress = (EditText) findViewById(R.id.email_field2);
        final EditText editSocialType = (EditText) findViewById(R.id.social_field);
        final EditText editSocial = (EditText) findViewById(R.id.social_field2);


        final Button buttonSave = (Button) findViewById(R.id.Save);
        final Button buttonCancel = (Button) findViewById(R.id.Cancel);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textFirstName = editFirstName.getText().toString();
                String textLastName = editLastName.getText().toString();
                String textTitle = editTitle.getText().toString();
                String textPhoneType = editPhoneType.getText().toString();
                String textPhoneNumber = editPhoneNumber.getText().toString();
                String textEmailType = editEmailType.getText().toString();
                String textEmailAddress = editEmailAddress.getText().toString();
                String textSocialType = editSocialType.getText().toString();
                String textSocial = editSocial.getText().toString();

                Log.i("Edit Contact", "ID: " + "" +
                        " First Name: " + textFirstName +
                        " Last Name: " + textLastName +
                        " Title: " + textTitle +
                        " Phone: " + textPhoneType + "-" + textPhoneNumber +
                        " Email:" + textEmailType + "-" + textEmailAddress +
                        "Social:" + textSocialType + "-" + textSocial);

                //Toast.makeText(this,"Contact saved",Toast.LENGTH_SHORT).show();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(this,"Changes discarded",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_remove:
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private InputStream retrieveStream(String url) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                Log.w(getClass().getSimpleName(),
                        "Error " + statusCode + " for URL " + url);
                return null;
            }
            HttpEntity getResponseEntity = getResponse.getEntity();
            return getResponseEntity.getContent();
        }

        catch (IOException e) {

            getRequest.abort();
            Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }
        return null;
    }
}
