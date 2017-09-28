package com.malmberg.matthew.beautifulbulldogs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;

public class NewBulldogActivity extends AppCompatActivity {

    private EditText newbulldog_name;
    private EditText newbulldog_age;
    private Realm realm;
    private ImageButton imageButton;
    private Button save_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bulldog);

        realm = Realm.getDefaultInstance();
        newbulldog_name = (EditText) findViewById(R.id.newbulldog_name);
        newbulldog_age = (EditText) findViewById(R.id.newbulldog_age);
        imageButton = (ImageButton) findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });

        save_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            if(!newbulldog_name.getText().toString().matches("")
                    &&!newbulldog_age.getText().toString().matches("")
                    && imageButton.getDrawable()!=null){
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm){
                        Bulldog bulldog = new Bulldog();
                        bulldog.setAge(newbulldog_age.getText().toString());
                        bulldog.setName(newbulldog_name.getText().toString());

                        bulldog.setId(realm.where(Bulldog.class).findAllSorted("id").last().getId()+1);


                        BitmapDrawable image = (BitmapDrawable) imageButton.getDrawable();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        image.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] imageInByte = baos.toByteArray();
                        bulldog.setImage(imageInByte);

                        realm.copyToRealm(bulldog);
                        finish();

                    }
                });
            }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==1 && resultCode == RESULT_OK){
            Bundle extras =data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButton.setImageBitmap(imageBitmap);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.close();
    }

}
