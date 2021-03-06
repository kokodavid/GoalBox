package com.david.goalbox.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.david.goalbox.R;

public class FirstActivity extends AppCompatActivity {
    private Button mFindRestaurantsButton;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mFindRestaurantsButton = (Button) findViewById(R.id.findSeasonButton);

        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String season = mLocationEditText.getText().toString();
                Intent intent = new Intent(FirstActivity.this, HomeActivity.class);
                intent.putExtra("season", season);
                startActivity(intent);
            }
        });
    }
}
