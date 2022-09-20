package com.example.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.lights.LightsManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView list;
    DBHelper dbHelper;
    MyCursorAdapter adapter;
    SQLiteDatabase db;
    EditText memo_txt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MemoFolder");


        memo_txt = findViewById(R.id.memo_txt);
        list = (ListView) findViewById(R.id.list);

        dbHelper = new DBHelper(this,"folderDB",null,1);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM folderTBL;",null);
        adapter = new MyCursorAdapter(this,cursor);

        list.setAdapter(adapter);

        Button add = findViewById(R.id.add_item);
        Button reset = findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = dbHelper.getWritableDatabase();
                dbHelper.onUpgrade(db,1,2);

                db.close();

                db = dbHelper.getReadableDatabase();


                Cursor  cursor = db.rawQuery("SELECT * FROM folderTBL;",null);
                adapter.changeCursor(cursor);

            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this);
                box.setMessage("삭제하시겠습니까?");
                box.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });

                box.setNegativeButton("cancle",null);

                box.show();

                return false;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),
                        MemoList.class);


                startActivityForResult(intent, 0);

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = dbHelper.getWritableDatabase();
                String msg = memo_txt.getText().toString();
                String sql = "INSERT INTO folderTBL VALUES(NULL,'" +
                        msg +
                        "', datetime('now'),"+
                        Calendar.getInstance().getTimeInMillis() +");";
                db.execSQL(sql);

                db.close();

                db = dbHelper.getReadableDatabase();

                Cursor  cursor = db.rawQuery("SELECT * FROM folderTBL;",null);
                adapter.changeCursor(cursor);



            }

        });
    }

}
