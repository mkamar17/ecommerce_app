package com.maryam.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, newUsernameEditText, newPasswordEditText;
    private Button createAccountButton;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); //set the content view to the signup layout

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        newUsernameEditText = findViewById(R.id.newUsernameEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        createAccountButton = findViewById(R.id.createAccountButton);

        dbHandler = new DBHandler(SignupActivity.this);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUnique;

                do {
                    // below line is to get data from all edit text fields.
                    String firstName = firstNameEditText.getText().toString();
                    String lastName = lastNameEditText.getText().toString();
                    String username = newUsernameEditText.getText().toString();
                    String password = newPasswordEditText.getText().toString();

                    // validating if the text fields are empty or not.
                    if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(SignupActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    isUnique = dbHandler.addNewShopper(firstName, lastName, username, password);
                    // on below line we are calling a method to add new
                    // course to sqlite data and pass all our values to it.
                    if (!isUnique) {
                        Toast.makeText(SignupActivity.this, "Account already exists or username taken.", Toast.LENGTH_SHORT).show();
                        //clearing the username field
                        newUsernameEditText.setText("");
                    } else {
                        Toast.makeText(SignupActivity.this, "Account has been created.", Toast.LENGTH_SHORT).show();

                        // after adding the data we are displaying a toast message.
                        firstNameEditText.setText("");
                        lastNameEditText.setText("");
                        newUsernameEditText.setText("");
                        newPasswordEditText.setText("");

                        //when done change activity
                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Close the LoginActivity
                    }
                } while (!isUnique);

            }
        });
    }
}