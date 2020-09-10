package com.columnhack.leaderboard.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.columnhack.leaderboard.R;
import com.columnhack.leaderboard.adapters.TabAdapter;
import com.columnhack.leaderboard.fragments.LearningLeadersFragment;
import com.columnhack.leaderboard.fragments.SkillIQLeadersFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabAdapter mTabAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

        FragmentManager fm = getSupportFragmentManager();


        mTabAdapter = new TabAdapter(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        LearningLeadersFragment learningLeadersFragment = new LearningLeadersFragment();
        SkillIQLeadersFragment skillIQLeadersFragment = new SkillIQLeadersFragment();
        mTabAdapter.addFragment(learningLeadersFragment, learningLeadersFragment.title);
        mTabAdapter.addFragment(skillIQLeadersFragment, skillIQLeadersFragment.title);

        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void startSubmitActivity(View view) {
        startActivity(new Intent(MainActivity.this, SubmitActivity.class));
    }
}