package com.example.mymemo;

import android.content.DialogInterface;
import android.content.Intent;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MemoList extends AppCompatActivity {
    MyAdapter adapter;
    ArrayList<String> memodata = new ArrayList<>();
    int item_id;

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return memodata.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View view = null;
            if(convertView != null)
                view = convertView;
            else{
                LayoutInflater inf = getLayoutInflater();
                view  = inf.inflate(R.layout.list_memo,viewGroup,false);
            }

//            TextView title = view.findViewById(R.id.memo_title);
            TextView msg = view.findViewById(R.id.memo_msg);
            TextView time = view.findViewById(R.id.memo_time);

//            title.setText("제목" + (position+1));
            msg.setText(memodata.get(position));
            // String ct = 현재시간
            time.setText(Calendar.getInstance().getTime().toString());


            return view;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        String s = intent.getStringExtra("msg_txt");


        item_id = intent.getIntExtra("item_id",0);


        memodata.set(item_id,s);


        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Memos");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.folderlist);



        EditText memo = findViewById(R.id.list_memo);

        Button addmemo = findViewById(R.id.addmemo);
        addmemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                memodata.add(memo.getText().toString());
                adapter.notifyDataSetChanged();

            }
        });
        ListView list = findViewById(R.id.memoview);

        adapter = new MyAdapter();

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int i, // item id
                                    long l ) { // position
                Intent intent = new Intent(getApplicationContext(),
                        Memo.class);

                intent.putExtra("memotitle", memodata.get(i));//memo_msg

                intent.putExtra("item_id",i);



                startActivityForResult(intent,0);

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item_id, long position) {

                AlertDialog.Builder box = new AlertDialog.Builder(MemoList.this);
                box.setMessage("삭제하시겠습니까?");
                box.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        memodata.remove(item_id);
                        adapter.notifyDataSetChanged();

                    }
                });

                box.setNegativeButton("cancle",null);

                box.show();

                return false;
            }
        });

        Button back = findViewById(R.id.back_btn);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                setResult(RESULT_OK,intent);
                finish();



            }
        });

    }
}