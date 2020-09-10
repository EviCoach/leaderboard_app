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
import com.columnhack.leaderboard.models.LearningLeader;

import java.util.List;

public class LearningLeadersRecyclerAdapter  extends RecyclerView.Adapter<LearningLeadersRecyclerAdapter.LearningLeaderHolder> {
    List<LearningLeader> mLeaders;
    Context mContext;

    public LearningLeadersRecyclerAdapter(Context context, List<LearningLeader> leaders){
        mContext = context;
        mLeaders = leaders;
    }

    @NonNull
    @Override
    public LearningLeaderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.learning_leaders_item, parent, false);
        return new LearningLeaderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeaderHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return mLeaders.size();
    }

    class LearningLeaderHolder extends RecyclerView.ViewHolder{
        // define fields here
        private ImageView topLearnerImg;
        private TextView topLearnerName;
        private TextView topLearnerDetail;

        public LearningLeaderHolder(@NonNull View itemView) {
            super(itemView);
            topLearnerImg = itemView.findViewById(R.id.top_learner_img_view);
            topLearnerName = itemView.findViewById(R.id.leader_name_txv);
            topLearnerDetail = itemView.findViewById(R.id.leader_detail_txv);
        }

        public void bind(int position){
            LearningLeader learningLeader = mLeaders.get(position);

            Glide.with(mContext).load(learningLeader.getBadgeUrl()).into(topLearnerImg);
            topLearnerName.setText(learningLeader.getName());
            String detailText = learningLeader.getHours() +
                    " learning hours, " + learningLeader.getCountry();
            topLearnerDetail.setText(detailText);
        }
    }
}
