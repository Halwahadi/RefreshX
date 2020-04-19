package edu.gsu.refreshx;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText) findViewById(R.id.Name);
        Password = (EditText) findViewById(R.id.Password);
        Login = (Button) findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Username.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "You must enter username", Toast.LENGTH_LONG).show();
                } else if (Username.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "You must enter password", Toast.LENGTH_LONG).show();
                } else {
                    validate(Username.getText().toString(), Password.getText().toString());
                }
            }
        });

        //Sign up Span text
        TextView signupLink = findViewById(R.id.signUpLink);
        String text = "Sign up for RefreshX";

        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };

        ss.setSpan(clickableSpan, 0, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        signupLink.setText(ss);
        signupLink.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void validate(String userName, String userPassword) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://76.20.217.228/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ApiResult> result = jsonPlaceHolderApi.authenticate(userName, userPassword);
        result.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(getApplicationContext(), "Successfully authenticated", Toast.LENGTH_LONG).show();
                    ApiResult.User authenticatedUser = response.body().getUsers().get(0);
                    Intent intent = new Intent(LoginActivity.this, DrinkRandomizer.class);
                    intent.putExtra("authenticated_user", new Gson().toJson(authenticatedUser));
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No internet access", Toast.LENGTH_LONG).show();
            }
        });
    }
}


