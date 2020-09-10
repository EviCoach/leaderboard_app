package com.columnhack.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.columnhack.leaderboard.R;
import com.columnhack.leaderboard.models.IQLeader;
import com.columnhack.leaderboard.models.LearningLeader;

import java.util.List;

public class SkillIQLeadersRecyclerAdapter extends RecyclerView.Adapter<SkillIQLeadersRecyclerAdapter.SkillIQLeaderHolder> {
    List<IQLeader> mLeaders;
    Context mContext;

    public SkillIQLeadersRecyclerAdapter(Context context, List<IQLeader> leaders){
        mLeaders = leaders;
        mContext = context;
    }

    @NonNull
    @Override
    public SkillIQLeaderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.skill_iq_leaders_item, parent, false);
        return new SkillIQLeaderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQLeaderHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mLeaders.size();
    }

    class SkillIQLeaderHolder extends RecyclerView
            .ViewHolder {
        private ImageView topLearnerImg;
        private TextView topLearnerName;
        private TextView topLearnerDetail;

        public SkillIQLeaderHolder(@NonNull View itemView) {
            super(itemView);
            topLearnerImg = itemView.findViewById(R.id.skill_iq_img_view);
            topLearnerName = itemView.findViewById(R.id.skill_iq_leader_name_txv);
            topLearnerDetail = itemView.findViewById(R.id.skill_iq_leader_detail_txv);
        }

        public void bind(int position) {
            IQLeader iqLeader = mLeaders.get(position);
            Glide.with(mContext).load(iqLeader.getBadgeUrl()).into(topLearnerImg);
            topLearnerName.setText(iqLeader.getName());
            String detailText = iqLeader.getScore() +
                    " skill IQ Score, " + iqLeader.getCountry();
            topLearnerDetail.setText(detailText);
        }
    }
}
