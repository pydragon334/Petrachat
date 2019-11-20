package com.petra.petrachat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//firebase

public class login_activity extends AppCompatActivity {

    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private Button m_login_btn;
    private ProgressDialog mLoginProgress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginProgress= new ProgressDialog(this);

        mEmail = (TextInputLayout)findViewById(R.id.login_email);
        mPassword = (TextInputLayout)findViewById(R.id.login_password);
        m_login_btn= (Button) findViewById(R.id.log_login);

        m_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getEditText().getText().toString();
                String password = mPassword.getEditText().getText().toString();

                //loading dialog
                mLoginProgress.setTitle("Sedang login");
                mLoginProgress.setMessage("Mohon ditunggu Petra People");
                mLoginProgress.setCanceledOnTouchOutside(false);
                mLoginProgress.show();

                login_user(email,password);
            }
        });

    }

    private void login_user(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mLoginProgress.dismiss();
                    Intent mainIntent= new Intent(login_activity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else {
                    mLoginProgress.hide();
                    Toast.makeText(login_activity.this,"Tidak bisa sign in, tolong cek kembali inputan anda!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
