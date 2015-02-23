package com.example.ggalasso.contactviewer_hw2_bpc;

import android.app.Activity;
import android.os.Bundle;
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

                }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editFirstName.setText("");
                editLastName.setText("");
                editTitle.setText("");
                editPhoneType.setText("");
                editPhoneNumber.setText("");
                editEmailType.setText("");
                editEmailAddress.setText("");
                editSocialType.setText("");
                editSocial.setText("");

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
