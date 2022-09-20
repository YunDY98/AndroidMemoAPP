package com.example.mymemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Memo  extends AppCompatActivity {

    DBHelper dbHelper;
    MyCursorAdapter adapter;
    SQLiteDatabase db;
    Cursor cursor;

    int item_id;


    private final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.editmemo, menu);

        imgGallery = findViewById(R.id.imgGallery);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.picture:
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
                break;

            case R.id.draw:
                Intent intent = new Intent(getApplicationContext(),
                        Draw.class);


                startActivityForResult(intent,0);
                break;
            case R.id.alarm:
                Intent alarm = new Intent(getApplicationContext(),
                        AlarmActivity.class);


                startActivityForResult(alarm,0);
                break;
            case R.id.save_btn:
                Intent save = new Intent(getApplicationContext(),MemoList.class);

                String s = ((EditText)findViewById(R.id.msg_txt)).getText().toString();

                save.putExtra("msg_txt",s);

                save.putExtra("item_id",item_id);



                setResult(RESULT_OK,save);

                finish();
                break;



        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode==RESULT_OK){
            if(requestCode==GALLERY_REQ_CODE){
                imgGallery.setImageURI(intent.getData());
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo);

        setTitle("Memo");


        Intent intent = getIntent();
        String s = intent.getStringExtra("memotitle");


        item_id = intent.getIntExtra("item_id",0);


        EditText txt = findViewById(R.id.msg_txt);


        txt.setText(s);

    }


}