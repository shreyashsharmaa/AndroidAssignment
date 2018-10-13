package com.example.shreyash.quizzler;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shreyash on 05/10/18.
 */

public class ApiResponse {




    private static final  String BASE_URL ="https://opentdb.com/";




    public static  PostService postService = null;

    public static PostService getService()
    {
        if(postService == null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            postService = retrofit.create(PostService.class);

        }
        return postService;

    }


public interface  PostService{

      @GET("api.php?amount=10&type=boolean")
        Call<QuestionBank> getQuestionList();

}

}