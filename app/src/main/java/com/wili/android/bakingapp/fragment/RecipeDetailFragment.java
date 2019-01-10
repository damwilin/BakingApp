package com.wili.android.bakingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.activity.detail.DetailViewModel;
import com.wili.android.bakingapp.activity.step.RecipeStepActivity;
import com.wili.android.bakingapp.adapter.RecipeIngredientAdapter;
import com.wili.android.bakingapp.adapter.RecipeStepAdapter;
import com.wili.android.bakingapp.data.models.Step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailFragment extends Fragment implements RecipeStepAdapter.StepOnClickListener {
    private DetailViewModel viewModel;
    private View rootView;

    private RecyclerView ingredientsRecyclerView;
    private RecyclerView stepRecyclerView;
    private MClickListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        getViews();
        viewModel = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);

        if (viewModel.getRecipe().getIngredients() != null) {
            ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            RecipeIngredientAdapter ingredientAdapter = new RecipeIngredientAdapter(viewModel.getRecipe().getIngredients());
            ingredientsRecyclerView.setAdapter(ingredientAdapter);
        }
        if (viewModel.getRecipe().getSteps() != null) {
            stepRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            RecipeStepAdapter stepAdapter = new RecipeStepAdapter(viewModel.getRecipe().getSteps(), this);
            stepRecyclerView.setAdapter(stepAdapter);
            stepRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                    DividerItemDecoration.VERTICAL));
        }
        return rootView;
    }

    private void getViews() {
        ingredientsRecyclerView = rootView.findViewById(R.id.recyclerViewRecipeIngredients);
        stepRecyclerView = rootView.findViewById(R.id.recyclerViewRecipeStep);
    }

    public void setMClickListener(MClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(Step step) {
        if (listener != null)
            listener.onClick(step);
        else {
            Gson gson = new Gson();
            Intent intent = new Intent(getActivity(), RecipeStepActivity.class);
            intent.putExtra(Step.STEP_KEY, gson.toJson(step));
            startActivity(intent);
        }
    }

    public interface MClickListener {
        void onClick(Step step);
    }
}
