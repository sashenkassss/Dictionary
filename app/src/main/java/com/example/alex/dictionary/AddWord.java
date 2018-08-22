package com.example.alex.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddWord extends AppCompatActivity {
    private Button Add;
    private EditText Word;
    private  EditText Translate;
    DataBaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        helper= DataBaseHelper.getInstance(this);
        addListenerOnButton();
    }
    public void  addListenerOnButton() {
        Word = (EditText)findViewById(R.id.word);
        Translate = (EditText)findViewById(R.id.translate);
        Add = (Button)findViewById(R.id.AddButton);
        Add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String word = Word.getText().toString();
                        String translate = Translate.getText().toString();
                        Type p = new Type(word,translate);
                        helper.add(p);
                            Intent intent = new Intent(AddWord.this, MainActivity.class);
                            startActivity(intent);

                    }
                }
        );
        }
}
