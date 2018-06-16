package com.wili.android.bakingapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.data.models.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.ViewHolder> {
    private List<Step> stepList;
    private StepOnClickListener stepOnClickListener;

    public RecipeStepAdapter(List<Step> stepList, StepOnClickListener stepOnClickListener) {
        this.stepList = stepList;
        this.stepOnClickListener = stepOnClickListener;
    }

    public interface StepOnClickListener{
        void onClick(Step step);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recipe_step, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Step currStep = stepList.get(position);
        String stepShortDescription = currStep.getShortDescription();
        holder.shortDescription.setText(stepShortDescription);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepOnClickListener.onClick(currStep);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shortDescription)
        TextView shortDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
