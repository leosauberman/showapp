package br.com.series.showapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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
    private FloatingActionButton adicionar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        serieArray = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.recycler_view);

        adicionar = (FloatingActionButton) findViewById(R.id.fab_add);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeriesActivity.this, TelaAddSerie.class));
            }
        });

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
