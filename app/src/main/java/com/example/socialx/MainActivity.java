package com.example.socialx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 pager;
    TabLayout tabLayout;
    TabItem loginTab, signupTab;
    Button button;
    boolean isSignupPageActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        loginTab = findViewById(R.id.login);
        signupTab = findViewById(R.id.signup);
        pager = findViewById(R.id.viewPager);
        button = findViewById(R.id.button3);
        PagerAdapter adapter = new PagerAdapter(this);
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSignupPageActive)
                    Toast.makeText(MainActivity.this, "Registration  complete", Toast.LENGTH_SHORT).show();
                else{
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        });
    }

    public void updateButton(boolean signupFragment){
        isSignupPageActive = signupFragment;
        if(isSignupPageActive)
            button.setText("Register");
        else
            button.setText("Login");
    }

}