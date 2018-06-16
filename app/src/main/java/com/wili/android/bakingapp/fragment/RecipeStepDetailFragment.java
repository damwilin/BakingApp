package com.wili.android.bakingapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.adapter.RecipeStepAdapter;
import com.wili.android.bakingapp.data.models.Ingredient;
import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.data.models.Step;

import java.util.List;

public class RecipeStepDetailFragment extends Fragment{
    private List<Ingredient> ingredientList;
    private List<Step> stepList;
    private View rootView;

    private TextView ingredientsView;
    private RecyclerView stepRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);
        getViews();

        if (ingredientList != null){
            StringBuilder builder = new StringBuilder();
            for (Ingredient ingredient : ingredientList){
                builder.append(ingredient.getName());
            }
            ingredientsView.setText(builder.toString());
        }
        if (stepList != null){
            stepRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            RecipeStepAdapter stepAdapter = new RecipeStepAdapter(stepList);
            stepRecyclerView.setAdapter(stepAdapter);
        }
        return rootView;
    }

    private void getViews() {
        ingredientsView = (TextView) rootView.findViewById(R.id.ingredients_list);
        stepRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewRecipeStep);
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }
}
