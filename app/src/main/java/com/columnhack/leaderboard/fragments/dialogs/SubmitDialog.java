package com.columnhack.leaderboard.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.columnhack.leaderboard.R;
import com.columnhack.leaderboard.services.LeadersBuilder;
import com.columnhack.leaderboard.services.LeadersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitDialog extends DialogFragment {
    String mFirstName, mLastName, mEmail, mGithubLink;
    public SubmitDialog(String firstName, String lastName, String email, String githubLink) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mGithubLink = githubLink;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.submit_dialog, null);
        ImageView cancelIcon = view.findViewById(R.id.cancel_btn);
        cancelIcon.setOnClickListener(v -> {
            SubmitDialog.this.dismiss();
            return;
        });
        Button yesBtn = view.findViewById(R.id.yes_btn);
        yesBtn.setOnClickListener(v -> {
            LeadersService leadersService = LeadersBuilder.buildService(LeadersService.class);
            Call<Void> submitForm = leadersService.submit(mFirstName, mLastName, mEmail, mGithubLink);
            submitForm.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Display success dialog
                        new SuccessDialog().show(getFragmentManager(), "success_dialog");
                        SubmitDialog.this.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Display failure dialog
                    new FailedDialog().show(getFragmentManager(), "fail_dialog");
                    SubmitDialog.this.dismiss();
                }
            });
        });
        builder.setView(view);
        return builder.create();
    }
}
