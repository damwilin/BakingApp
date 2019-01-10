package com.wili.android.bakingapp.activity.step;


import android.os.Bundle;

import com.google.gson.Gson;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.data.models.Step;
import com.wili.android.bakingapp.fragment.RecipeStepDetailFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

public class RecipeStepActivity extends AppCompatActivity {
    private RecipeStepViewModel viewModel;

    private RecipeStepDetailFragment recipeStepDetailFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        viewModel = ViewModelProviders.of(this).get(RecipeStepViewModel.class);
        getStepFromIntent();
        initializeFragment();
        displayRecipeStepDetail(viewModel.getStep());
    }

    private void getStepFromIntent() {
            Gson gson=new Gson();
            String strObj = getIntent().getStringExtra(Step.STEP_KEY);
        viewModel.setStep(gson.fromJson(strObj, Step.class));
    }

    private void initializeFragment(){
        recipeStepDetailFragment = new RecipeStepDetailFragment();
        fragmentManager = getSupportFragmentManager();
    }

    public void displayRecipeStepDetail(Step step) {
        fragmentManager.beginTransaction()
                .add(R.id.step_container, recipeStepDetailFragment)
                .commit();
    }

}
