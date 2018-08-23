package com.example.alex.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeActivity extends AppCompatActivity {

   public EditText word;
    EditText translate;
    Button changeButton;
    DataBaseHelper helper;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_change, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            Type type = Words.dictionary.get(MainActivity.position);
            helper.remove(type.id);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Type type = Words.dictionary.get(MainActivity.position);
        word = (EditText)findViewById(R.id.ChangeWord);
        translate = (EditText)findViewById(R.id.ChangeTranslate);
        changeButton = (Button)findViewById(R.id.changeButton);
        word.setText(type.word);
        translate.setText(type.translate);
        helper= DataBaseHelper.getInstance(this);
        addListenerOnButton();
    }
    public void  addListenerOnButton() {
        changeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Word = word.getText().toString();
                        String Translate = translate.getText().toString();
                        Type type = Words.dictionary.get(MainActivity.position);
                        type.word=Word;
                        type.translate=Translate;
                        helper.change(type,String.valueOf(type.id));
                        //Words.Set(MainActivity.position,type);
                        Intent intent = new Intent(ChangeActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                }
        );
    }
}
