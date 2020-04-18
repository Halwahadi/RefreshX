package edu.gsu.refreshx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private Button signupbtn;
    private EditText name, username, password, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://76.20.217.228/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        name = findViewById(R.id.nameInput);
        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.usernameInput);
        email = findViewById(R.id.emailInput);

        signupbtn = findViewById(R.id.signUpButton);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String userName = username.getText().toString();
                String passwd = password.getText().toString();
                String mail = email.getText().toString();
                Call<ApiResult> result = jsonPlaceHolderApi.createUser(Name, userName, passwd, mail);
                System.out.println(result.request().toString());
                if(Name == "" || userName == ""  || passwd == ""  || mail == "" ){
                    Toast.makeText(getApplicationContext(), "You must fill all the blanks", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(Name.length() > 4 && userName.length() > 4 && passwd.length() > 4 && mail.length() > 4 ){
                    result.enqueue(new Callback<ApiResult>() {
                        @Override
                        public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Got error response", Toast.LENGTH_LONG).show();
                                try {
                                    JSONObject errorObj = new JSONObject(response.errorBody().string());
                                    Toast.makeText(getApplicationContext(), errorObj.getString("message"), Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Created account successful", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(RegisterActivity.this, LandingPage.class);
                                startActivity(i);
                                finish();;
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResult> call, Throwable t) {
                            System.out.println(t);
                            Toast.makeText(getApplicationContext(), "Cannot connect to server", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LandingPage.class);
                startActivity(i);
                finish();
            }
        });
    }
}
