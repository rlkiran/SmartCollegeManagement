package com.example.my_project003;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.Objects;
import adapters_custom.Circular_adapter;
import cards.Circular_Item;

public class Circulars_activity extends AppCompatActivity {

    RecyclerView cRecyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cr_ref = db.collection("Circulars");
    private Circular_adapter adapter;
    FirestoreRecyclerOptions<Circular_Item> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulars_activity);
        cRecyclerView = findViewById(R.id.circular_recycle);
        cRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setCirculars();

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void setCirculars() {
        Query query = cr_ref.orderBy("title",Query.Direction.DESCENDING);

        options = new FirestoreRecyclerOptions.Builder<Circular_Item>()
                .setQuery(query,Circular_Item.class)
                .build();

        adapter = new Circular_adapter(options);
        cRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cRecyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Objects.requireNonNull(cRecyclerView.getAdapter()).notifyItemRemoved(i);
            }
        }).attachToRecyclerView(cRecyclerView);

        adapter.setOnItemClickListener((documentSnapshot, position) -> {
            String id = documentSnapshot.getId();
            Intent i = new Intent(Circulars_activity.this,ViewCircular.class);
            i.putExtra("id",id);
            Toast.makeText(Circulars_activity.this,
                    "Position: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });
    }

}
