package com.example.ggalasso.contactviewer_hw2_bpc;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
//import android.widget.Toast;
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



public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactManager cm = ContactManager.getInstance(this);
        // initialize the list view
        setListAdapter(new ContactAdapter(this, R.layout.contact_item, cm.getContactList()));
        //setListAdapter(new ContactAdapter(this, R.layout.contact_item, ContactManager.getAll()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ContactManager cm = ContactManager.getInstance(this);
        ContactAdapter ca = new ContactAdapter(this, R.layout.contact_item, cm.getContactList());
        ca.notifyDataSetChanged();
        setListAdapter(ca);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

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
                onSearch();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openAdd(){
        Intent intent = new Intent(this, AddContact.class);
        startActivity(intent);
    }

    private void onSearch(){

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

        //GG: Store data to be passed into next activity into a bundle
        //See: http://www.101apps.co.za/articles/passing-data-between-activities.html
        Bundle contactInfo = new Bundle();
        contactInfo.putInt("id", contact.getId());

        //GG: Create Intent to switch to the contact detail screen. Clicking on the contact switches to the next screen.
        //See here: http://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, ContactDetails.class);
        intent.putExtras(contactInfo);
        startActivity(intent);
    }

    class ContactAdapter extends ArrayAdapter<Contact> {

        public ContactAdapter(Context context, int resource, ArrayList<Contact> objects) {
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

            nameView.setText(contact.getFirstName()+ " " + contact.getLastName());
            titleView.setText(contact.getContactTitle());
            phoneView.setText(contact.getPhoneNumber());

            return view;
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
