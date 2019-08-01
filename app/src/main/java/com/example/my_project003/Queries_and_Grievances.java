package com.example.my_project003;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Queries_and_Grievances extends AppCompatActivity {

    EditText e,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries_and__grievances);
        e = findViewById(R.id.ed_query);
        e2 = findViewById(R.id.ed_roll);
    }

    public void submitQuery(View view) {
        if(!e.getText().toString().isEmpty() && !e2.getText().toString().isEmpty()) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> QueryData = new HashMap<>();
            QueryData.put("roll", e2.getText().toString());
            QueryData.put("query", e.getText().toString());

            db.collection("Queries").document(e2.getText().toString())
                    .set(QueryData)
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Query Posted Successfully", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Query Posted Successfully", Toast.LENGTH_SHORT).show());
        }

    }
}
