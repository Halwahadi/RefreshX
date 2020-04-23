package edu.gsu.refreshx;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DrinkRandomizer extends AppCompatActivity {
    public int drinkId;
    private ApiResult.User authenticatedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_randomizer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        if(item.getItemId() == R.id.favorites_menu){
            authenticatedUser = new Gson().fromJson(getIntent().getStringExtra("authenticated_user"), ApiResult.User.class);
            intent = new Intent(this, FavoriteList.class);
            intent.putExtra("authenticated_user", new Gson().toJson(authenticatedUser));
        } else if(item.getItemId() == R.id.signout_menu){
            intent = new Intent(this, LoginActivity.class);
        }
        else{
            intent = new Intent(this, DrinkRandomizer.class);
        }
        startActivity(intent);
        return true;
    }

}
