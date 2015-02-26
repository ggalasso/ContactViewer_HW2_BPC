package com.example.ggalasso.contactviewer_hw2_bpc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddContact extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        final EditText addFirstName = (EditText) findViewById(R.id.first_name_field);
        final EditText addLastName = (EditText) findViewById(R.id.last_name_field);
        final EditText addTitle = (EditText) findViewById(R.id.title_field);
        final EditText addPhoneType = (EditText) findViewById(R.id.phone_field);
        final EditText addPhoneNumber = (EditText) findViewById(R.id.phone_field2);
        final EditText addEmailType = (EditText) findViewById(R.id.email_field);
        final EditText addEmailAddress = (EditText) findViewById(R.id.email_field2);
        final EditText addSocialType = (EditText) findViewById(R.id.social_field);
        final EditText addSocial = (EditText) findViewById(R.id.social_field2);


        final Button buttonSave = (Button) findViewById(R.id.Save);
        final Button buttonCancel = (Button) findViewById(R.id.Cancel);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textFirstName = addFirstName.getText().toString();
                String textLastName = addLastName.getText().toString();
                String textTitle = addTitle.getText().toString();
                String textPhoneType = addPhoneType.getText().toString();
                String textPhoneNumber = addPhoneNumber.getText().toString();
                String textEmailType = addEmailType.getText().toString();
                String textEmailAddress = addEmailAddress.getText().toString();
                String textSocialType = addSocialType.getText().toString();
                String textSocial = addSocial.getText().toString();

                Contact c = new Contact(textFirstName,textLastName, textTitle, textPhoneType, textPhoneNumber, textEmailType, textEmailAddress, textSocialType, textSocial, 12);
                ContactManager cm = ContactManager.getInstance(v.getContext());
                cm.addContact(c);


                Log.i("Add Contact", "ID: " + "" +
                        " First Name: " + textFirstName +
                        " Last Name: " + textLastName +
                        " Title: " + textTitle +
                        " Phone: " + textPhoneType + "-" + textPhoneNumber +
                        " Email:" + textEmailType + "-" + textEmailAddress +
                        "Social:" + textSocialType + "-" + textSocial);

                finish();
                }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addFirstName.setText("");
                addLastName.setText("");
                addTitle.setText("");
                addPhoneType.setText("");
                addPhoneNumber.setText("");
                addEmailType.setText("");
                addEmailAddress.setText("");
                addSocialType.setText("");
                addSocial.setText("");

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
