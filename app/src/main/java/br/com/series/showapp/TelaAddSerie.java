package br.com.series.showapp;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TelaAddSerie extends AppCompatActivity {


    private EditText titulo;

    private TextView tv_sinopse;
    private EditText et_sinopse;

    private Spinner spinner;

    private Button add_imagem;

    private FloatingActionButton fab_add;
    private Uri imageUri, downloadUrl;

    private ProgressBar progress;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference ref = db.getReference("Serie");
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference().child("imagens");
    private String id;
    private static int RC_PHOTO_PICKER = 2;
    private static int RC_OK = 2;
    private String nome, desc, categoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_add_serie);

        id = ref.push().getKey();
        progress = (ProgressBar) findViewById(R.id.pb);

        titulo = (EditText) findViewById(R.id.et_titulo);
        nome = titulo.getText().toString();

        et_sinopse = (EditText) findViewById(R.id.et_sinopse);
        desc = et_sinopse.getText().toString();

        tv_sinopse = (TextView) findViewById(R.id.tv_sinopse);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorias, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        categoria = spinner.getSelectedItem().toString();

        add_imagem = (Button) findViewById(R.id.add_ft_button);

        add_imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "complete"), RC_PHOTO_PICKER);
            }
        });
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add_act_addSerie);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkURL()){
                    addShow(nome, desc, downloadUrl, categoria);
                }
            }
        });

    }

    private void addShow(String titulo, String sinopse, Uri imagem, String categoria) {
        Serie show = new Serie(titulo, sinopse, imagem, categoria);
        ref.child(id).setValue(show);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_OK) {
            if (resultCode == RESULT_OK) {

                Toast.makeText(this, "Show", Toast.LENGTH_SHORT).show();

            }
            else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
                finish();

            }
        } else if (resultCode == RESULT_OK && requestCode == RC_PHOTO_PICKER) {

            imageUri = data.getData();
            uploadToFB();
        }
    }

    private void uploadToFB(){
        progress.setVisibility(View.VISIBLE);
        // Get a reference to store file at chat_photos/<FILENAME>
        StorageReference photoRef = storageRef.child(imageUri.getLastPathSegment());

        // Upload file to Firebase Storage
        photoRef.putFile(imageUri)
                .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // When the image has successfully uploaded, we get its download URL
                        downloadUrl = taskSnapshot.getDownloadUrl();
                        progress.setVisibility(View.GONE);
                    }
                });
    }
    private boolean checkURL(){
        if(downloadUrl != null){
            return true;
        }
        else{
            downloadUrl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/showapp-85598.appspot.com/o/imagens%2Fpadrao.jpg?alt=media&token=8b4ffdf7-26f8-4d72-b700-5ad4d2f39be7");
            return true;
        }
    }

}
