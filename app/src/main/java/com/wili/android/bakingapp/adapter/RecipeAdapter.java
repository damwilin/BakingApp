package com.wili.android.bakingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.data.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe> recipeList;
    private RecipeOnClickListener onClickListener;

    public RecipeAdapter(RecipeOnClickListener onClickListener) {
        this.recipeList = new ArrayList<>();
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
                    .load(R.drawable.ic_error)
                    .into(holder.recipeImage);

        } else {
            Picasso.get()
                    .load(imagePath)
                    .centerCrop()
                    .placeholder(R.drawable.ic_error)
                    .error(R.drawable.ic_error)
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

    public void setData(List<Recipe> newRecipeList) {
        if (recipeList != null) {
            PostDiffCallback postDiffCallback = new PostDiffCallback(recipeList, newRecipeList);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);
            recipeList.clear();
            recipeList.addAll(newRecipeList);
            diffResult.dispatchUpdatesTo(this);
        } else {
            recipeList = newRecipeList;
        }
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

    class PostDiffCallback extends DiffUtil.Callback {
        private final List<Recipe> oldRecipes;
        private final List<Recipe> newRecipes;

        public PostDiffCallback(List<Recipe> oldRecipes, List<Recipe> newRecipes) {
            this.oldRecipes = oldRecipes;
            this.newRecipes = newRecipes;
        }

        @Override
        public int getOldListSize() {
            return oldRecipes.size();
        }

        @Override
        public int getNewListSize() {
            return newRecipes.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldRecipes.get(oldItemPosition).getId() == newRecipes.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldRecipes.get(oldItemPosition).equals(newRecipes.get(newItemPosition));
        }
    }
}
