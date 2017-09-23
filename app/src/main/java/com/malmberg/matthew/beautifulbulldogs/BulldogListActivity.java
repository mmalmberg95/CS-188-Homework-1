package com.malmberg.matthew.beautifulbulldogs;

<<<<<<< HEAD
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class BulldogListActivity extends AppCompatActivity {

    private ListView bulldogList;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BulldogListActivity extends AppCompatActivity {

    private TextView textView;

>>>>>>> a93806e9dcf89395c779051199380f81163c07b2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog_list);

<<<<<<< HEAD
        ArrayList<Bulldog> bulldogs = new ArrayList<Bulldog>();
        bulldogList = (ListView) findViewById(R.id.bulldog_list);

        Bulldog bulldog1 = new Bulldog();
        bulldog1.setAge("10");
        bulldog1.setName("Penny");

        Bulldog bulldog2 = new Bulldog();
        bulldog2.setAge("4");
        bulldog2.setName("Julie");

        bulldogs.add(bulldog1);
        bulldogs.add(bulldog2);

        final BulldogArrayAdapter adapter = new BulldogArrayAdapter(this, bulldogs);
        bulldogList.setAdapter(adapter);

        bulldogList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                final Bulldog bulldog = (Bulldog) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(view.getContext(), BulldogActivity.class);
                intent.putExtra("bulldog", (Serializable) bulldog);
                startActivity(intent);
            }
        });
=======
        textView = (TextView) findViewById(R.id.textView);

        String email = getIntent().getStringExtra("email");
        textView.setText(email);
>>>>>>> a93806e9dcf89395c779051199380f81163c07b2
    }
}
