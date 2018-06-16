package com.wili.android.bakingapp.activity.step;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.data.models.Step;
import com.wili.android.bakingapp.fragment.RecipeStepDetailFragment;

public class RecipeStepActivity extends AppCompatActivity implements RecipeStepView {
    private RecipeStepPresenter presenter;

    private Step step;
    private RecipeStepDetailFragment recipeStepDetailFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        presenter = new RecipeStepPresenter(this);
        getStepFromDetail();
        initializeFragment();
    }

    private void getStepFromDetail(){
        Gson gson=new Gson();
        String strObj = getIntent().getStringExtra(Step.STEP_KEY);
        step = gson.fromJson(strObj, Step.class);
    }

    private void initializeFragment(){
        recipeStepDetailFragment = new RecipeStepDetailFragment();
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void displayRecipeStepDetail(Step step) {
        fragmentManager.beginTransaction()
                .add(R.id.step_container, recipeStepDetailFragment)
                .commit();
    }
}
