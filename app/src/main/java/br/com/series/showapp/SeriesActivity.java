package br.com.series.showapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SeriesActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerAdapter adapter;
    private ArrayList<Serie> serieArray;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Series");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        serieArray = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        serieArray.clear();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot series : dataSnapshot.getChildren()){
                    Serie serie = series.getValue(Serie.class);

                    serieArray.add(serie);
                }
                adapter = new RecyclerAdapter(serieArray, SeriesActivity.this);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
