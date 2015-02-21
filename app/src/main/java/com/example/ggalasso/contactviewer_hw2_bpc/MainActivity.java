package com.example.ggalasso.contactviewer_hw2_bpc;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;
//import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListAdapter(new ContactAdapter(this, R.layout.contact_item, Contact.getAll()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        //restoreactionbar()
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add:
                openAdd();
                return true;
            case R.id.action_search:
                //openSearch();
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openAdd(){
        Intent intent = new Intent(this, ContactDetails.class);
        startActivity(intent);
    }
    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getListView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l        The ListView where the click happened
     * @param v        The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id       The row id of the item that was clicked
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Contact contact = (Contact)getListAdapter().getItem(position);
        //Toast.makeText(this, "Clicked " + contact.getName() + " (" + id + ")", Toast.LENGTH_LONG).show();

        //Intent intent = new Intent(this, ContactDetails.class);
        //startActivity(intent);

        //GG: Store data to be passed into next activity into a bundle
        //See: http://www.101apps.co.za/articles/passing-data-between-activities.html
        Bundle contactInfo = new Bundle();
        contactInfo.putInt("id", contact.getId());
        contactInfo.putString("name", contact.getName());
        contactInfo.putString("title", contact.getTitle());
        contactInfo.putString("phone", contact.getPhone());

        //GG: Create Intent to switch to the contact detail screen. Clicking on the contact switches to the next screen.
        //See here: http://developer.android.com/training/basics/firstapp/starting-activity.html

        Intent intent = new Intent(this, ContactDetails.class);
        intent.putExtras(contactInfo);
        startActivity(intent);

    }

    class ContactAdapter extends ArrayAdapter<Contact> {

        public ContactAdapter(Context context, int resource, Contact[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView == null) {
                view = getLayoutInflater().inflate(R.layout.contact_item, parent, false);
            } else {
                view = convertView;
            }
            Contact contact = getItem(position);
            TextView nameView = (TextView)view.findViewById(R.id.item_name);
            TextView titleView = (TextView)view.findViewById(R.id.item_title);
            TextView phoneView = (TextView)view.findViewById(R.id.item_phone);

            nameView.setText(contact.getName());
            titleView.setText(contact.getTitle());
            phoneView.setText(contact.getPhone());

            return view;
        }
    }

}
