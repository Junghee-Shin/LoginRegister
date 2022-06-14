package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {

    EditText inputUsername, inputPassword, inputEmail, inputFullname;
    Button btnSignUp;
    String baseUrl = "http://192.168.10.66/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intent intent = getIntent();

        inputFullname = (EditText) findViewById(R.id.inputFullname);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname, username, password, email;
                fullname = inputFullname.getText().toString();
                email = inputEmail.getText().toString();
                username = inputUsername.getText().toString();
                password = inputPassword.getText().toString();

                if( !username.equals("") && !password.equals("") ){
                    signUp(fullname, email, username, password);
                }else {
                    Toast.makeText(getApplicationContext(),"All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void signUp(String fullname, String email, String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiRes> call = apiService.signUp(fullname,email,username,password);

        call.enqueue(new Callback<ApiRes>() {
            @Override
            public void onResponse(Call<ApiRes> call, Response<ApiRes> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().toString().equals("200")){
                        Toast.makeText(getApplicationContext(), response.body().getInfo(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiRes> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
