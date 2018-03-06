package com.upb.master.lab2task1;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText menuName = findViewById(R.id.menuName);
        final EditText menuDescription = findViewById(R.id.menuDescription);
        final EditText menuPrice = findViewById(R.id.menuPrice);

        Button nextButton = findViewById(R.id.secondActivity);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("data", menuName.getText().toString());
                startActivity(i);
            }
        });

        Button addButton = findViewById(R.id.addItem);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues cv = new ContentValues();
                cv.put("name", menuName.getText().toString());
                cv.put("desc", menuDescription.getText().toString());
                cv.put("price", Integer.parseInt(menuPrice.getText().toString()));

                Uri uri = getContentResolver().insert(Uri.parse(ItemsProvider.tableUri), cv);
                Log.d("[com][smd][lab2]","Result of adding new item: " + uri.toString());
            }
        });

        Button thirdActivity = findViewById(R.id.thirdActivity);
        thirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ItemsActivity.class);
                startActivity(i);
            }
        });
    }
}
