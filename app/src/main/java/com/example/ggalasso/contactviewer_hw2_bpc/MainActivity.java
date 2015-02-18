package com.example.ggalasso.contactviewer_hw2_bpc;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new ContactAdapter(this, R.layout.contact_item, Contact.getAll()));
    }
//test comment

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //test comment
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
        Toast.makeText(this, "Clicked " + contact.getName() + " (" + id + ")", Toast.LENGTH_LONG).show();
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
