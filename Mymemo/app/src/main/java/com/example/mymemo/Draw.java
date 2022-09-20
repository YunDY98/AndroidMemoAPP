package com.example.mymemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class Draw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(new MyView(this));

        setTitle("Draw");

//        //디바이스 회전시 값 유지를 하기위한 코드
//        Resources r = Resources.getSystem();
//        Configuration config = r.getConfiguration();
//        onConfigurationChanged(config);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.drawmenu, menu);


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){


            case R.id.eraser:
                setContentView(new MyView(this));

                break;



        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        //현재 디바이스의 방향성을 체크...
//
//        switch(newConfig.orientation){
//            case Configuration.ORIENTATION_LANDSCAPE:
//                Toast.makeText(getApplicationContext(),"가로",Toast.LENGTH_SHORT).show();
//                break;
//            case Configuration.ORIENTATION_PORTRAIT:
//                Toast.makeText(getApplicationContext(),"세로",Toast.LENGTH_SHORT).show();
//                return;
//        }
//
//    }
}
