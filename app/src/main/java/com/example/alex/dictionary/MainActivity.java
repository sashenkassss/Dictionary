package com.example.alex.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    public static ListView listView ;
    public static int position;
    public static DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Words.clear();
        helper= DataBaseHelper.getInstance(this);
        listView = (ListView)findViewById(R.id.lvMain);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                //удаления слова из спискa
                AlertDialog.Builder d_builder = new AlertDialog.Builder(MainActivity.this);
                d_builder.setMessage("Удалить слово ?")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                helper.remove(pos);
                                Update();
                                dialogInterface.cancel();
                            }
                        })
                         .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                                 dialogInterface.cancel();
                             }
                         });
                         AlertDialog alert = d_builder.create();
                        // alert.setTitle("Удалить слово ?");
                         alert.show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Words.clear();
        Words.dictionary = helper.allElement();
        Update();
        super.onResume();
    }

    public void Update()
   {
       Words.Sort();
       listView = (ListView)findViewById(R.id.lvMain);
       BoxAdapter adapter = new BoxAdapter(this,Words.dictionary);
       listView.setAdapter(adapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                           @Override
                                           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                               position = i;
                                               Intent intent = new Intent(MainActivity.this,ChangeActivity.class);
                                               startActivity(intent);
                                           }
                                       }
       );
   }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
          Intent intent = new Intent(this, AddWord.class);
          startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
