package com.example.makda.pentago;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.makda.pentago.network.SpiceActivity;
import com.example.makda.pentago.network.requests.userRequests.CreateUserRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import static com.example.makda.pentago.Utils.toMD5;

/**
 * Created by Makda on 2016-01-20.
 */
public class CreateUserActivity extends SpiceActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    SharedPreferences.Editor prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_layout);
        loginEditText = (EditText)findViewById(R.id.login_add);
        passwordEditText = (EditText)findViewById(R.id.password_add);
        emailEditText =(EditText)findViewById(R.id.email_add);
        prefs = getSharedPreferences("login", MODE_PRIVATE).edit();
    }

    public void onCreateUser(View view){
        final String login = loginEditText.getText().toString();
        final String password = toMD5(passwordEditText.getText().toString(), login);
        final String email = emailEditText.getText().toString();
        CreateUserRequest userRequest = new CreateUserRequest(login, password, email);
        getSpiceManager().execute(userRequest, new RequestListener<Void>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(CreateUserActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRequestSuccess(Void aVoid) {
                prefs.putString("name", login);
                prefs.commit();
                Toast.makeText(CreateUserActivity.this, "Checkout your email", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
