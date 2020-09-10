package com.columnhack.leaderboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.columnhack.leaderboard.R;
import com.columnhack.leaderboard.adapters.LearningLeadersRecyclerAdapter;
import com.columnhack.leaderboard.adapters.SkillIQLeadersRecyclerAdapter;
import com.columnhack.leaderboard.models.IQLeader;
import com.columnhack.leaderboard.models.LearningLeader;
import com.columnhack.leaderboard.services.LeadersBuilder;
import com.columnhack.leaderboard.services.LeadersService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeadersFragment extends Fragment {
    public String title = "Skill IQ Leaders";
    private LeadersService mSkillIQLeaders;
    private RecyclerView mSkillIQRecyclerView;
    private SkillIQLeadersRecyclerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make call to the learning leaders api here
        // this returns the same type you passed into it
        mSkillIQLeaders = LeadersBuilder.buildService(LeadersService.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);
        mSkillIQRecyclerView = view.findViewById(R.id.skill_iq_leader_rcv);

        Call<List<IQLeader>> skillIQLeaders = mSkillIQLeaders.getSkillIQLeaders();
        skillIQLeaders.enqueue(new Callback<List<IQLeader>>() {
            @Override
            public void onResponse(Call<List<IQLeader>> call, Response<List<IQLeader>> response) {
                mAdapter = new SkillIQLeadersRecyclerAdapter(getActivity(), response.body());
                mSkillIQRecyclerView.setAdapter(mAdapter);
                mSkillIQRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<IQLeader>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error getting skill IQ leaders", Toast.LENGTH_SHORT)
                        .show();
            }
        });


        return view;
    }
}
