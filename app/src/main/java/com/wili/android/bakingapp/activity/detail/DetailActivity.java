package com.wili.android.bakingapp.activity.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.activity.step.RecipeStepActivity;
import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.data.models.Step;
import com.wili.android.bakingapp.fragment.RecipeDetailFragment;
import com.wili.android.bakingapp.fragment.RecipeStepDetailFragment;
import com.wili.android.bakingapp.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements RecipeDetailFragment.MClickListener {
    @BindView(R.id.ingredients_container)
    FrameLayout ingredientsContainer;

    private RecipeDetailFragment recipeDetailFragment;
    private FragmentManager fragmentManager;
    @BindView(R.id.step_details_container)
    FrameLayout stepDetailsContainer;
    private DetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        getRecipeFromIntent();
        initializeFragment();
        displayRecipeDetail();
        configureDisplayContent();
    }

    private void getRecipeFromIntent(){
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra(Recipe.RECIPE_KEY);
        viewModel.setRecipe(gson.fromJson(strObj, Recipe.class));
    }
    private void initializeFragment(){
        recipeDetailFragment = new RecipeDetailFragment();
        fragmentManager = getSupportFragmentManager();
    }

    private void displayRecipeDetail() {
        fragmentManager.beginTransaction()
                .add(R.id.ingredients_container, recipeDetailFragment)
                .commit();
    }

    private void displayStepDetail() {
        fragmentManager.beginTransaction()
                .add(R.id.step_details_container, RecipeStepDetailFragment.getNewInstance())
                .commit();
    }

    private void configureDisplayContent() {
        if (!Utils.isTablet(this)) {
            stepDetailsContainer.setVisibility(View.GONE);
        } else {
            stepDetailsContainer.setVisibility(View.VISIBLE);
            recipeDetailFragment.setMClickListener(this);
            displayStepDetail();
        }
    }

    @Override
    public void onClick(Step step) {
        if (Utils.isTablet(this)) {
            viewModel.setStep(step);
            fragmentManager.beginTransaction().replace(R.id.step_details_container, RecipeStepDetailFragment.getNewInstance()).commit();
            Log.d("Detail Activity", viewModel.getStep().getVideoURL());
        } else {
            Gson gson = new Gson();
            Intent intent = new Intent(this, RecipeStepActivity.class);
            intent.putExtra(Step.STEP_KEY, gson.toJson(step));
            startActivity(intent);
        }
    }
}
