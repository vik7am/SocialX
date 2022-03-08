package com.example.socialx;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Fragment {

    EditText email, password;
    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        email = view.findViewById(R.id.editTextTextEmailAddress);
        password = view.findViewById(R.id.editTextTextPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    public void validateData(){
        //Toast.makeText(getActivity(), "Toast is working", Toast.LENGTH_SHORT).show();
        String rawEmail = email.getText().toString().trim();
        String rawPassword = password.getText().toString().trim();
        if(TextUtils.isEmpty(rawEmail)){
            email.setError("Email is Required");
            return;
        }
        if(TextUtils.isEmpty(rawPassword)){
            password.setError("Password is Required");
            return;
        }
        if(rawPassword.length() < 6){
            password.setError("Password must be grater than 6");
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(rawEmail, rawPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
                else{
                    Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).updateButton(false);
    }
}