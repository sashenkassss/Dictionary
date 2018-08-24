package com.example.alex.dictionary;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

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
                        Type p = new Type(word,translate,1);
                            helper.add(p);
                            Intent intent = new Intent(AddWord.this, MainActivity.class);
                            startActivity(intent);

                    }
                }
        );
        }
    @Override
    protected void onResume() {
        Word = (EditText)findViewById(R.id.word);
        Translate = (EditText)findViewById(R.id.translate);
        Word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               String string = null;

                try {
                    string =JsonHelper.getJsonStringYandex("father");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("mLog", string);

            }});
        super.onResume();
}
}
