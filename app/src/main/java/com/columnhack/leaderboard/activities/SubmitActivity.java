package com.columnhack.leaderboard.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.columnhack.leaderboard.R;
import com.columnhack.leaderboard.fragments.dialogs.SubmitDialog;
import com.columnhack.leaderboard.services.LeadersBuilder;
import com.columnhack.leaderboard.services.LeadersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    private EditText firstNameEt;
    private EditText lastNameEt;
    private EditText emailEt;
    private EditText githubLinkEt;
    private Button submitBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        Toolbar toolbar = findViewById(R.id.submit_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        firstNameEt = findViewById(R.id.first_name_et);
        lastNameEt = findViewById(R.id.last_name_et);
        emailEt = findViewById(R.id.email_et);
        githubLinkEt = findViewById(R.id.github_et);

        submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(v -> {
            // check if the data in all edit texts are valid
            String firstName = firstNameEt.getText().toString();
            String lastName = firstNameEt.getText().toString();
            String email = firstNameEt.getText().toString();
            String githubLink = firstNameEt.getText().toString();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || githubLink.isEmpty()) {
                Toast.makeText(SubmitActivity.this, "Field must not be empty", Toast.LENGTH_SHORT)
                        .show();
                return;
            } else {

                // Display confirmation dialog

                // the fields are not empty and assume they are filled with valid information
                // make an http post to submit the data
                SubmitDialog submitDialog = new SubmitDialog(firstName, lastName, email, githubLink);
                submitDialog.setCancelable(false);
                submitDialog.show(getSupportFragmentManager(), "");
            }
        });
    }
}
