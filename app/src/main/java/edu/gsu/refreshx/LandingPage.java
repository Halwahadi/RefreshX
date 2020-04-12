package edu.gsu.refreshx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
    }

    public void register (View view) {
        Intent i = new Intent(LandingPage.this, RegisterActivity.class);
        startActivity(i);

        finish();
    }
}
