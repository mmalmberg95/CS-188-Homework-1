package com.malmberg.matthew.beautifulbulldogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;

public class BulldogActivity extends AppCompatActivity {

    private TextView Name;
    private Spinner rating;
    private Button vote;
    private ImageView picture;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog);

        Name = (TextView) findViewById(R.id.addName);
        rating = (Spinner) findViewById(R.id.spinner);
        picture = (ImageView) findViewById(R.id.vote_picture);

        realm = Realm.getDefaultInstance();

        String id = (String) getIntent().getStringExtra("bulldog");

        Bulldog bulldog = realm.where(Bulldog.class).equalTo("id", id).findFirst();
                /*(Bulldog) getIntent().getSerializableExtra("bulldog");*/
        Name.setText(bulldog.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.close();
    }

}
