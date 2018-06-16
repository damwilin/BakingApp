package com.wili.android.bakingapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.data.models.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe> recipeList;
    private RecipeOnClickListener onClickListener;

    public RecipeAdapter(List<Recipe> recipeList, RecipeOnClickListener onClickListener) {
        this.recipeList = recipeList;
        this.onClickListener = onClickListener;
    }


    public interface RecipeOnClickListener{
        void onClick(Recipe recipe);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Recipe currRecipe = recipeList.get(position);
        String name = currRecipe.getName();
        String imagePath = currRecipe.getImageURL();
        holder.recipeName.setText(name);


        if (imagePath.isEmpty() || imagePath.equals("")){
            Picasso.get()
                    .load(R.drawable.cake_placeholder)
                    .into(holder.recipeImage);

        } else {
            Picasso.get()
                    .load(imagePath)
                    .centerCrop()
                    .placeholder(R.drawable.cake_placeholder)
                    .error(R.drawable.cake_placeholder)
                    .into(holder.recipeImage);
        }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(currRecipe);
                }
            });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipe_name)
        TextView recipeName;
        @BindView(R.id.recipe_image)
        ImageView recipeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
