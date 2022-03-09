package com.example.socialx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ViewPager2 pager;
    TabLayout tabLayout;
    TabItem loginTab, signupTab;
    PagerAdapter adapter;
    CardView cardView;
    TextView textView;
    ProgressDialog dialog;
    FirebaseAuth firebaseAuth;
    boolean isSignupPageActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        checkLoginStatus();

        tabLayout = findViewById(R.id.tabLayout);
        loginTab = findViewById(R.id.login);
        signupTab = findViewById(R.id.signup);
        pager = findViewById(R.id.viewPager);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Please Wait");
        cardView = findViewById(R.id.cardBottom);
        textView = findViewById(R.id.submit);

        getSupportActionBar().setElevation(0);
        initialize();

    }

    private void checkLoginStatus() {
        //check firebase login status
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
    }

    public void initialize(){
        //connects tabLayout to the view pager
        adapter = new PagerAdapter(this);
        pager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("LOGIN ");
                }
                else {
                    tab.setText("Signup ");
                }
            }
        }).attach();
        //validates data depending upon active fragment
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSignupPageActive){
                    ((Signup)adapter.signupFragment).validateData();
                }
                else{
                    ((Login)adapter.loginFragment).validateData();
                }
            }
        });
    }

    public void updateButton(boolean signupFragment){
        //Update Submit cardView Text and function
        isSignupPageActive = signupFragment;
        if(isSignupPageActive)
            textView.setText("Register");
        else
            textView.setText("Login");
    }

}