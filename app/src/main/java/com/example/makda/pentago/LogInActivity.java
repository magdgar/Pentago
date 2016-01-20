package com.example.makda.pentago;

/**
 * Created by Makda on 2016-01-18.
 */

import android.content.Intent;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.makda.pentago.model.User;
import com.example.makda.pentago.network.SpiceActivity;
import com.example.makda.pentago.network.requests.CreateUserRequest;
import com.example.makda.pentago.network.requests.DeleteUserRequest;
import com.example.makda.pentago.network.requests.UpdateUserRequest;
import com.example.makda.pentago.network.requests.UserRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;


public class LogInActivity extends SpiceActivity {

    private EditText loginEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        loginEditText = (EditText)findViewById(R.id.login_add);
        passwordEditText = (EditText)findViewById(R.id.password_add);
    }

    public void onLogIn(View view){
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        UserRequest userRequest = new UserRequest(login, password);

        final Intent resIntent = new Intent(this, ShapeChooseActivity.class);
        getSpiceManager().execute(userRequest, new RequestListener<User>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                spiceException.printStackTrace();
            }

            @Override
            public void onRequestSuccess(User user) {
                startActivity(resIntent);
                Log.v("ASDASD", "SUCCESS!!!");
            }
        });


    }

    public void onCreateUser(View view){
        Intent resIntent = new Intent(this, CreateUserActivity.class);
        startActivity(resIntent);
    }

    public void onDeleteUser(View view) {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        DeleteUserRequest userRequest = new DeleteUserRequest(login, password);
        getSpiceManager().execute(userRequest, new RequestListener<Void>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {

            }

            @Override
            public void onRequestSuccess(Void aVoid) {
                Log.v("ASDASD", "DEATH!!!!!");
            }
        });
    }

    public void onUpdateUser(View view) {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        UserRequest userRequest = new UserRequest(login, password);
        getSpiceManager().execute(userRequest, new RequestListener<User>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                spiceException.printStackTrace();
            }

            @Override
            public void onRequestSuccess(final User user) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LogInActivity.this);

                alert.setTitle("New Password");
                alert.setMessage("Enter New Password: ");

                final EditText input = new EditText(LogInActivity.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String newPassword = input.getText().toString();
                        UpdateUserRequest updateUserRequest = new UpdateUserRequest(user.getName(), user.getPassword(), newPassword);
                        getSpiceManager().execute(updateUserRequest, new RequestListener<Void>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {

                            }

                            @Override
                            public void onRequestSuccess(Void aVoid) {
                                Log.v("ASDASD", "password updated!");
                            }
                        });
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
        });
    }
}