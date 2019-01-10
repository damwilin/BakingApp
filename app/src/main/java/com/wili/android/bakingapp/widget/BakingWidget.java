package com.wili.android.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.activity.main.MainActivity;
import com.wili.android.bakingapp.data.models.Ingredient;
import com.wili.android.bakingapp.data.models.Recipe;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, Recipe recipe, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("WIDGET_EXTRA", "CAME_FROM_WIDGET");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        views.removeAllViews(R.id.ingredients_listView);
        views.setOnClickPendingIntent(R.id.recipe_title, pendingIntent);
        views.setTextViewText(R.id.recipe_title, recipe.getName());

        for (Ingredient ingredient : recipe.getIngredients()) {
            RemoteViews rvIngredient = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
            StringBuilder builder = new StringBuilder();
            builder.append(ingredient.getName() + " ");
            builder.append(ingredient.getQuantity() + " ");
            builder.append(ingredient.getMeasureType());
            rvIngredient.setTextViewText(R.id.widget_ingredient_item, builder.toString());
            views.addView(R.id.ingredients_listView, rvIngredient);
            Log.d(BakingWidget.class.getSimpleName(), "Added: " + ingredient.getName());
        }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateRecipeWidgets(Context context, Recipe recipe, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, recipe, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

