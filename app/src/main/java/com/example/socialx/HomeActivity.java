package com.example.socialx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.socialx.models.NewsHeadlines;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements RequestManager.OnFetchDataListener{


    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchView = findViewById(R.id.search_bar);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news Articles");
        dialog.show();
        setListeners();
    }

    private void setListeners() {
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(this, "general", null);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles of "+ query);
                dialog.show();
                RequestManager manager = new RequestManager(HomeActivity.this);
                manager.getNewsHeadlines(HomeActivity.this, "general", query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFetchData(List<NewsHeadlines> list, String message) {
        if(list.isEmpty()){
            Toast.makeText(HomeActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
        else{
            showNews(list);
        }
        dialog.dismiss();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout_menu:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}