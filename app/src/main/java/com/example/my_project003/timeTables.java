package com.example.my_project003;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class timeTables extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView t1;
    PhotoView iv1;
    ProgressBar pb;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_tables);
        t1 = findViewById(R.id.msg);
        iv1 = findViewById(R.id.imageView);
        Spinner ttb = findViewById(R.id.timetables);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);


        ttb.setOnItemSelectedListener(this);

        List<String> timetables = new ArrayList<>();
        timetables.add("SELECT THE TIMETABLE");
        timetables.add("CSE Second Year First Section");  //"https://firebasestorage.googleapis.com/v0/b/smart-college-management.appspot.com/o/CSE_2_1_1.jpg?alt=media&token=11bf08be-3637-491a-a2bc-225404b7a82b"
        timetables.add("CSE Second Year Second Section"); //"https://firebasestorage.googleapis.com/v0/b/smart-college-management.appspot.com/o/CSE_2_1_2.jpg?alt=media&token=bc3995a3-8de1-45bf-ae0f-64dcf34093eb"
        timetables.add("CSE Third Year First Section"); //"https://firebasestorage.googleapis.com/v0/b/smart-college-management.appspot.com/o/CSE_3_1_1.jpg?alt=media&token=2405fb89-7961-4e40-ad45-22c8d1238f10"
        timetables.add("CSE Third Year Second Section"); //"https://firebasestorage.googleapis.com/v0/b/smart-college-management.appspot.com/o/CSE_3_1_2.jpg?alt=media&token=5e5dfa19-4be5-45b9-8b93-fceccbc98030"
        timetables.add("CSE Final Year First Section"); //"https://firebasestorage.googleapis.com/v0/b/smart-college-management.appspot.com/o/CSE_4_1_1.jpg?alt=media&token=985aad18-69e5-40d8-a218-5d4a20fb0404"
        timetables.add("CSE Final Year Second Section"); //"https://firebasestorage.googleapis.com/v0/b/smart-college-management.appspot.com/o/CSE_4_1_2.jpg?alt=media&token=af39d510-ae68-4b1e-87e3-27e90ac6df43"

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timetables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ttb.setAdapter(dataAdapter);


    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position>0) {
            pb.setVisibility(View.VISIBLE);
            String item = parent.getItemAtPosition(position).toString();
            t1.setText(item);
            DatabaseReference imgRef = database.getReference(item);
            imgRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String imageurl = dataSnapshot.getValue(String.class);
                    Picasso.get()
                            .load(imageurl)
                            .fit()
                            .into(iv1, new Callback() {
                                @Override
                                public void onSuccess() {
                                    pb.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Failed to read value
                    Toast.makeText(getApplicationContext(), "You are a fucking piece of shit 2", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else {
            t1.setText("Please Select the TimeTable to display");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
