package br.com.series.showapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TelaAddSerie extends AppCompatActivity {


    private EditText titulo;

    private TextView tv_sinopse;
    private EditText et_sinopse;

    private Spinner spinner;

    private Button add_imagem;

    private Button fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_add_serie);

        titulo = (EditText)findViewById(R.id.et_titulo);
        et_sinopse = (EditText)findViewById(R.id.et_sinopse);
        tv_sinopse = (TextView)findViewById(R.id.tv_sinopse);
        spinner = (Spinner) findViewById(R.id.spinner);
        add_imagem = (Button) findViewById(R.id.add_ft_button);
        fab_add = (Button) findViewById(R.id.fab_add);
    }
}
