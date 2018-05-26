package com.wili.android.bakingapp.data.network;

import com.wili.android.bakingapp.data.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET(ApiParam.DATA_PATH)
    Call<List<Recipe>> getData();
}
