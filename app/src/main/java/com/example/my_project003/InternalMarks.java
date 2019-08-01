package com.example.my_project003;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InternalMarks extends AppCompatActivity {

    PhotoView itmarks;
    String key;
    Spinner internals_list;
    ProgressBar pb;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference itRef = database.getReference("InternalMarks");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_marks);
        itmarks = findViewById(R.id.internals_img);
        internals_list = findViewById(R.id.spin_internals);
        pb = findViewById(R.id.it_pb);

        itRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final List<String> classes = new ArrayList<>();
                classes.add("Select the class");

                for (DataSnapshot roomSnapshot : dataSnapshot.getChildren()) {
                    String className = roomSnapshot.getKey();
                    classes.add(className);
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<>(InternalMarks.this, android.R.layout.simple_spinner_item, classes);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                internals_list.setAdapter(areasAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void loadItImage(View view) {
        key = internals_list.getSelectedItem().toString();
        if(!key.equals("Select the class")) {
            pb.setVisibility(View.VISIBLE);
            DatabaseReference urlRef = database.getReference("InternalMarks").child(key).child("url");
            urlRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    Picasso.get().load(value).fit().into(itmarks);
                    Toast.makeText(InternalMarks.this, "Success", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.GONE);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Toast.makeText(InternalMarks.this, "not Success", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "please select the class", Toast.LENGTH_SHORT).show();
        }
    }
}
