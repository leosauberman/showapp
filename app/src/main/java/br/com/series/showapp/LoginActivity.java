package br.com.series.showapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText emailTV;
    private EditText passwordET;
    private ProgressBar loginProgress;
    private String email;
    private String password;
    private Button signIn;
    private Button signUp;

    //Firebase
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTV = (EditText) findViewById(R.id.email);
        passwordET = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.email_sign_in_button);
        signUp = (Button) findViewById(R.id.email_sign_up_button);

        loginProgress = (ProgressBar) findViewById(R.id.login_progress);

        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    System.out.println("onAuthStateChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    // User is signed out
                    System.out.println("onAuthStateChanged:signed_out");
                }
            }
        };

        signIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailTV.getText().toString();
                password = passwordET.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, R.string.error_invalid_email,
                            Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, R.string.error_invalid_password,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    signin();
                }
            }
        });

        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailTV.getText().toString();
                password = passwordET.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, R.string.error_invalid_email,
                            Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, R.string.error_invalid_password,
                            Toast.LENGTH_SHORT).show();
                }
                else if(password.length() < 6){
                    Toast.makeText(LoginActivity.this, "Senha muito curta, menor que 6 digitos",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    signup();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }

    private void signup(){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("createUserWithEmail:onComplete:" + task.isSuccessful());

                        loginProgress.setVisibility(View.VISIBLE);

                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                            loginProgress.setVisibility(View.GONE);
                        }
                        else{
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            loginProgress.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void signin(){

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("signInWithEmail:onComplete:" + task.isSuccessful());

                        loginProgress.setVisibility(View.VISIBLE);

                        if (!task.isSuccessful()) {
                            System.out.println("signInWithEmail:failed" + task.getException());
                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                            loginProgress.setVisibility(View.GONE);
                        }
                        else{
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            loginProgress.setVisibility(View.GONE);
                        }
                    }
                });
    }
}

