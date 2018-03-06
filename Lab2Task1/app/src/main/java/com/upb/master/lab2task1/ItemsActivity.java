package com.upb.master.lab2task1;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {
    private ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        itemList = (ListView) findViewById(R.id.item_list);

        List<MyMenuItem> items = new ArrayList<MyMenuItem>();
        String[] columns = {"name", "desc", "price"};

        Cursor cursor = getContentResolver().query(Uri.parse(ItemsProvider.tableUri),
                columns, null, null, null);
        if (!cursor.moveToFirst()) {
            Log.e("[com][smd][lab2]", "db returned invalid cursor or no items in db");
        } else {
            while(!cursor.isAfterLast()) {
                items.add(new MyMenuItem(cursor.getString(0), cursor.getInt(2), cursor.getString(1)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        /* TODO - query the database and add the results to items */

        MenuAdapter adapter = new MenuAdapter(this, items);
        itemList.setAdapter(adapter);


    }
}
