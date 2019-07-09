package hr.tomljanovic.matko.seminarskirad.adapters;

import hr.tomljanovic.matko.seminarskirad.HackerNewsAPI;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {
    public static final String URL = "https://hacker-news.firebaseio.com/";

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static HackerNewsAPI requestApi = retrofit.create(HackerNewsAPI.class);

    public static HackerNewsAPI getRequestApi() {
        return requestApi;
    }
}
