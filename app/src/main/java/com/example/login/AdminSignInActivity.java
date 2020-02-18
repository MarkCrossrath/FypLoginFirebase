package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminSignInActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseAuth mAuth;
    EditText editTextAdminEmail;
    EditText editTextAdminPassword;
    EditText editTextAdminNumber;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_signin);
        mAuth = FirebaseAuth.getInstance();

        editTextAdminEmail = findViewById(R.id.editTextAdminEmail);
        editTextAdminPassword = findViewById(R.id.editTextAdminPassword);
        progressBar = findViewById(R.id.progressbar);
        editTextAdminNumber = findViewById(R.id.editTextAdminNumber);



        findViewById(R.id.buttonAdminSignIn).setOnClickListener(this);




    }

    private void AdminSign(){

        String email = editTextAdminEmail.getText().toString().trim();
        String password = editTextAdminPassword.getText().toString().trim();
        String adminNo = editTextAdminNumber.getText().toString().trim();


        if (email.isEmpty()){
            editTextAdminEmail.setError("Email is Required ");
            editTextAdminEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextAdminEmail.setError("Please enter a valid email");
            editTextAdminEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextAdminPassword.setError("Password is Required");
            editTextAdminPassword .requestFocus();
            return;
        }

        if(password.length()<6){
            editTextAdminPassword.setError("Password must be greater than 6 characters");
            editTextAdminPassword.requestFocus();
            return;
        }
        if (adminNo.isEmpty()){
            editTextAdminNumber.setError("Admin number is required ");
            editTextAdminNumber.requestFocus();
            return;
        }





        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){


                    Intent intent = new Intent(AdminSignInActivity.this, AdminMenuActivity.class);
                    //intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }


                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonAdminSignIn:
                AdminSign();
                break;


        }

    }
}



