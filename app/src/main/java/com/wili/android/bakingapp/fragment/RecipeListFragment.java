package com.wili.android.bakingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.activity.detail.DetailActivity;
import com.wili.android.bakingapp.activity.main.MainActivity;
import com.wili.android.bakingapp.adapter.RecipeAdapter;
import com.wili.android.bakingapp.data.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends Fragment implements RecipeAdapter.RecipeOnClickListener{
    private List<Recipe> recipeList;
    private View rootView;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        getViews();

        if (recipeList!= null){
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            RecipeAdapter recipeAdapter = new RecipeAdapter(recipeList, this);
            recyclerView.setAdapter(recipeAdapter);
        }
        return rootView;
    }

    public void setRecipeList(List<Recipe> recipeList){
        this.recipeList = recipeList;
    }

    private void getViews(){
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleListView);
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        //intent.putExtra(recipe);
        startActivity(intent);
    }
}
