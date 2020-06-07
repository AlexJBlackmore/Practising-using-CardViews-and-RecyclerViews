package com.alexblackmore.recyclerviewpractise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddSport extends AppCompatActivity {

    EditText titleET;
    EditText infoET;
    EditText descET;
    Button addSportBtn;
    View.OnClickListener myAddSportListener;

    public static final String EXTRA_TITLE = "com.alexblackmore.recyclerviewpractise.TITLE";
    public static final String EXTRA_INFO = "com.alexblackmore.recyclerviewpractise.INFO";
    public static final String EXTRA_DESC = "com.alexblackmore.recyclerviewpractise.DESC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sport);

        titleET = findViewById(R.id.enterTitleET_wdg);
        infoET = findViewById(R.id.enterInfoET_wdg);
        descET = findViewById(R.id.enterDescET_wdg);
        addSportBtn = findViewById(R.id.addSportBtn_wdg);

        myAddSportListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sportAdded = new Intent();
                sportAdded.putExtra(EXTRA_TITLE, titleET.getText().toString());
                sportAdded.putExtra(EXTRA_INFO, infoET.getText().toString());
                sportAdded.putExtra(EXTRA_DESC, descET.getText().toString());
                setResult(RESULT_OK, sportAdded);
                finish();
            }
        };

        addSportBtn.setOnClickListener(myAddSportListener);

    }
}
