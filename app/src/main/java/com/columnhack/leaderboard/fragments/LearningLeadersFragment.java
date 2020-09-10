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
import com.columnhack.leaderboard.models.LearningLeader;
import com.columnhack.leaderboard.services.LeadersBuilder;
import com.columnhack.leaderboard.services.LeadersService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment {
    public String title = "Learning Leaders";
    private LeadersService mLeadersService;
    private LearningLeadersRecyclerAdapter mAdapter;
    private RecyclerView mLeadersRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make call to the learning leaders api here
        // this returns the same type you passed into it
        mLeadersService = LeadersBuilder.buildService(LeadersService.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        mLeadersRecyclerView = view.findViewById(R.id.learning_leaders_rcv);

        Call<List<LearningLeader>> learningLeaders = mLeadersService.getLearningLeaders();
        learningLeaders.enqueue(new Callback<List<LearningLeader>>() {
            @Override
            public void onResponse(Call<List<LearningLeader>> call, Response<List<LearningLeader>> response) {
                mAdapter = new LearningLeadersRecyclerAdapter(getActivity(), response.body());
                mLeadersRecyclerView.setAdapter(mAdapter);
                mLeadersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            }

            @Override
            public void onFailure(Call<List<LearningLeader>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error getting leaders information", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        return view;
    }
}
