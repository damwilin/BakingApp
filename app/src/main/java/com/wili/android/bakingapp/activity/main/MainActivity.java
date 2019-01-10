package com.wili.android.bakingapp.activity.main;

import android.os.Bundle;

import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.fragment.RecipeListFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private RecipeListFragment recipeListFragment;
    private FragmentManager fragmentManager;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initializeFragment();
        displayRecipeListFragment();

    }

    private void initializeFragment(){
        recipeListFragment = new RecipeListFragment();
        fragmentManager = getSupportFragmentManager();
    }


    public void displayRecipeListFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.list_container, recipeListFragment)
                .commit();
    }

}
