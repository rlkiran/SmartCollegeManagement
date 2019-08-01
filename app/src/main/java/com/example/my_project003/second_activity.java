package com.example.my_project003;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class second_activity extends AppCompatActivity {

    TextView t1,t2;
    ImageView i1;
    String title,descData;
    int img;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);
        t1 = findViewById(R.id.test_tv);
        t2 = findViewById(R.id.Description);
        i1 = findViewById(R.id.test_iv);

        Intent i = getIntent();
        title = Objects.requireNonNull(i.getExtras()).getString("Title");
        img = i.getExtras().getInt("Thumbnail");
        descData = Objects.requireNonNull(i.getExtras()).getString("DescData");

        t1.setText(title);
        i1.setImageResource(img);
        t2.setText(descData);
    }
}
