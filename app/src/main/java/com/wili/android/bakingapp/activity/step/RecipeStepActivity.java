package com.wili.android.bakingapp.activity.step;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        if (savedInstanceState != null){
            step = savedInstanceState.getParcelable(Step.STEP_KEY);
            Log.d(RecipeStepActivity.class.getSimpleName(), "Step restored: " + step.getId());
        }
        else {
            getStepFromDetail();
        }
        getStepFromDetail();
        initializeFragment();
        displayRecipeStepDetail(step);
    }

    private void getStepFromDetail(){
        if (step == null){
            Gson gson=new Gson();
            String strObj = getIntent().getStringExtra(Step.STEP_KEY);
            step = gson.fromJson(strObj, Step.class);
            Log.d(RecipeStepActivity.class.getSimpleName(),"Video URl: " + step.getVideoURL());
        }
    }

    private void initializeFragment(){
        recipeStepDetailFragment = new RecipeStepDetailFragment();
        recipeStepDetailFragment.setStep(step);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void displayRecipeStepDetail(Step step) {
        fragmentManager.beginTransaction()
                .add(R.id.step_container, recipeStepDetailFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Step.STEP_KEY, step);
    }
}
