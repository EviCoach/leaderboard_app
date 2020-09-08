package com.columnhack.leaderboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.columnhack.leaderboard.R;

public class SkillIQLeadersFragment extends Fragment {
    public String title = "Skill IQ Leaders";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);
        return view;
    }
}
