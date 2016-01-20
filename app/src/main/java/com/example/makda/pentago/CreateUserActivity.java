package com.example.makda.pentago;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.makda.pentago.network.SpiceActivity;
import com.example.makda.pentago.network.requests.CreateUserRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by Makda on 2016-01-20.
 */
public class CreateUserActivity extends SpiceActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private EditText emailEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_layout);
        loginEditText = (EditText)findViewById(R.id.login_add);
        passwordEditText = (EditText)findViewById(R.id.password_add);
        emailEditText =(EditText)findViewById(R.id.email_add);
    }

    public void onCreateUser(View view){
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String email = emailEditText.getText().toString();
        CreateUserRequest userRequest = new CreateUserRequest(login, password, email);
        getSpiceManager().execute(userRequest, new RequestListener<Void>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {

            }

            @Override
            public void onRequestSuccess(Void aVoid) {
                Log.v("ASDASD", "SUCCESS onCreate!!!");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
