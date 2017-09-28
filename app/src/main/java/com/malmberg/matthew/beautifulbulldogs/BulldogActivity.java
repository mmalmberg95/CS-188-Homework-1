package com.malmberg.matthew.beautifulbulldogs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;

public class BulldogActivity extends AppCompatActivity {

    private TextView textView;
    private Realm realm;
    private EditText newbulldog_name;
    private EditText newbulldog_age;
    private User owner;
    private ImageView bulldogImage;
    private TextView nameView;
    private Button voteButton;
    private Spinner rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog);

        //textView = (TextView) findViewById(R.id.textView);
        nameView = (TextView) findViewById(R.id.name_label);
        rating = (Spinner) findViewById(R.id.rating_spinner);
        bulldogImage = (ImageView) findViewById(R.id.bulldog_image);
        voteButton = (Button) findViewById(R.id.vote_button);

        realm = Realm.getDefaultInstance();

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");

        newbulldog_name = (EditText) findViewById(R.id.newbulldog_name);
        newbulldog_age = (EditText) findViewById(R.id.newbulldog_age);

        String id = (String) getIntent().getStringExtra("bulldog");
        final Bulldog bulldog = realm.where(Bulldog.class).equalTo("id", id).findFirst();

        String username = (String) getIntent().getStringExtra("username");
        owner = realm.where(User.class).equalTo("username", username).findFirst();

        //Bulldog bulldog = realm.where(Bulldog.class).equalTo("id", id).findFirst();
                /*(Bulldog) getIntent().getSerializableExtra("bulldog");*/

        if(bulldog.getimage() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(bulldog.getimage(), 0, bulldog.getimage().length);
            bulldogImage.setImageBitmap(bmp);
        }
        nameView.setText(bulldog.getName());

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating.setAdapter(adapter);

        rating.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Vote vote = new Vote();
                        vote.setOwner(owner);
                        vote.setRating(Integer.valueOf(rating.getSelectedItem().toString()));
                        bulldog.appendVote(vote);

                        finish();
                    }
                });
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.close();
    }

}
