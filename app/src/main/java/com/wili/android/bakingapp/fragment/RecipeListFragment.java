package com.wili.android.bakingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.activity.detail.DetailActivity;
import com.wili.android.bakingapp.activity.main.MainViewModel;
import com.wili.android.bakingapp.adapter.RecipeAdapter;
import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.utils.Utils;
import com.wili.android.bakingapp.widget.WidgetService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeListFragment extends Fragment implements RecipeAdapter.RecipeOnClickListener {
    private View rootView;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private MainViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        getViews();
        recipeAdapter = new RecipeAdapter(this);

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        viewModel.getRecipeList().observe(this, recipes -> {
            recipeAdapter.setData(recipes);
        });

        setLayoutManager();
        recyclerView.setAdapter(recipeAdapter);
        return rootView;
    }

    private void getViews() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleListView);
    }

    private void setLayoutManager() {
        if (Utils.isTablet(getActivity()))
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(Recipe recipe) {
        Gson gson = new Gson();
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Recipe.RECIPE_KEY, gson.toJson(recipe));
        WidgetService.updateRecipeWidgets(getActivity().getBaseContext(), recipe);
        startActivity(intent);
    }
}
