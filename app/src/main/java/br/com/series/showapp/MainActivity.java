package br.com.series.showapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Integer[] mNames = {
            R.string.acao, R.string.comedia,
            R.string.cult, R.string.drama,
            R.string.heroi, R.string.romance,
            R.string.terror, R.string.documentario,
            R.string.animacao, R.string.outros
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final GridView gridview = (GridView) findViewById(R.id.grid);
        gridview.setAdapter(new ImageAdapter(this, MainActivity.this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(MainActivity.this, SeriesActivity.class);
                i.putExtra("categoria", mNames[position].toString());
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
